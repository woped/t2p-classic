package de.dhbw.text2process.wrapper;

import de.dhbw.text2process.enums.PhraseType;
import de.dhbw.text2process.models.worldModel.Action;
import de.dhbw.text2process.models.worldModel.SpecifiedElement;
import de.dhbw.text2process.models.worldModel.Specifier;
import de.dhbw.text2process.processors.worldmodel.Constants;
import de.dhbw.text2process.processors.worldmodel.transform.SearchUtils;
import de.saar.coli.salsa.reiter.framenet.*;
import de.saar.coli.salsa.reiter.framenet.fncorpus.*;
import edu.mit.jwi.item.POS;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrameNetFunctionality {

  static Logger logger = LoggerFactory.getLogger(FrameNetFunctionality.class);

  private static FrameNet f_frameNet;
  private static AnnotationCorpus f_corpus;
  private FrameNetInitializer fni;

  public FrameNetFunctionality() {
    fni = FrameNetInitializer.getInstance();
    f_frameNet = fni.getFN();
    f_corpus = fni.getCorpus();
  }

  public static void printAllFrames() {
    // Iterate over all frames
    for (Frame frame : f_frameNet.getFrames()) {

      // Print out the name of each frame
      logger.info(frame.getName());

      // Iterate over all frame elements of the frame
      for (FrameElement fe : frame.frameElements()) {

        // Print out the name and semantic types of the frame element
        logger.info("  " + fe.getName() + " / " + Arrays.toString(fe.getSemanticTypes()));
      }
      logger.info(" This frame uses the frames: ");

      // Iterate over all frames that are used by this frame
      for (Frame uses : frame.uses()) {

        // Print out their names
        logger.info("  " + uses.getName());
      }
    }
  }

  public static void determineSpecifierFrameElement(SpecifiedElement element, Specifier spec) {
    if ("of".equals(spec.getHeadWord()) && !(element instanceof Action)) {
      spec.setPhraseType(PhraseType.GENITIVE);
    } else {
      HashMap<FrameElement, Integer> _countMap = new HashMap<FrameElement, Integer>();
      boolean _verb = element instanceof Action;
      WordNetFunctionality wnf = new WordNetFunctionality();
      String _baseForm = wnf.getBaseForm(element.getName(), false, _verb ? POS.VERB : POS.NOUN);
      if (_verb && ((de.dhbw.text2process.models.worldModel.Action) element).getPrt() != null) {
        _baseForm += " " + ((Action) element).getPrt();
      }
      Collection<LexicalUnit> _units = f_frameNet.getLexicalUnits(_baseForm, (_verb ? "v" : "n"));
      int _testedLUs = _units.size();
      for (LexicalUnit lu : _units) {
        AnnotatedLexicalUnit _alu = f_corpus.getAnnotation(lu);
        if (_alu != null) {
          for (FEGroupRealization fegr : _alu.getFERealizations()) {
            for (ValencePattern pat : fegr.getPatterns()) {
              for (ValenceUnit vu : pat.getUnits()) {
                if (vu.getPhraseType().startsWith("PP")
                    && vu.getPhraseType().contains("[" + spec.getHeadWord() + "]")) {
                  if (_countMap.containsKey(vu.getFrameElement())) {
                    Integer _newVal = _countMap.get(vu.getFrameElement()) + pat.getTotalCount();
                    _countMap.put(vu.getFrameElement(), _newVal);
                  } else {
                    _countMap.put(vu.getFrameElement(), pat.getTotalCount());
                  }
                }
              }
            }
          }
        }
      }
      FrameElement _best = (FrameElement) SearchUtils.getMaxCountElement(_countMap);
      if (_best == null) {
        spec.setPhraseType(PhraseType.UNKNOWN);
      } else {
        spec.setPhraseType(toPT(_best));
        spec.setFrameElement(_best);
      }
      if (Constants.DEBUG_FRAME_ASSIGNMENT) {
        logger.info(
            "Valence Units for: " + _baseForm + " (" + _testedLUs + ") - " + spec.getPhrase());
        for (FrameElement fe : _countMap.keySet()) {
          logger.info("\t\t " + _countMap.get(fe) + " " + fe + " " + fe.getCoreTypeString());
        }
        logger.info("\t+ Best: " + _best);
      }
    }
  }

  private static PhraseType toPT(FrameElement fe) {
    switch (fe.getCoreType()) {
      case Core:
        return PhraseType.CORE;
      case Core_Unexpressed:
        return PhraseType.CORE;
      case Extra_Thematic:
        return PhraseType.EXTRA_THEMATIC;
      case Peripheral:
        return PhraseType.PERIPHERAL;
      default:
        return PhraseType.UNKNOWN;
    }
  }
}
