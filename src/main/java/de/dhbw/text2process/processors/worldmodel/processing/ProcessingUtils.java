/** modified taken from https://github.com/FabianFriedrich/Text2Process */
package de.dhbw.text2process.processors.worldmodel.processing;

import de.dhbw.text2process.models.worldModel.Action;
import de.dhbw.text2process.models.worldModel.Actor;
import de.dhbw.text2process.models.worldModel.ExtractedObject;
import de.dhbw.text2process.processors.worldmodel.Constants;
import de.dhbw.text2process.wrapper.WordNetFunctionality;
import edu.stanford.nlp.trees.Tree;
import java.util.List;

public class ProcessingUtils {

  private static String[] f_beForms = {"be", "am", "are", "is", "was", "were", "been"};

  private static String[] f_personPronouns = {
    "I", "you", "he", "she", "we", "you", "they", "me", "him", "her", "us", "them"
  };
  private static String[] f_3rdpersonPronouns = {"he", "she", "it", "they", "him", "her", "them"};
  private static String[] f_inanimatePronouns = {"it", "they", "them", "which"};
  private static String[] f_determiner = {"the", "this", "that", "these", "those"};
  private static String[] f_ActionResolutionDeterminer = {"this", "that"};
  private static String[] f_relativeClausePronouns = {"who", "whose", "which", "that"};

  /**
   * determiens whether the given word can be used to introduce a relative clause e.g. which, who or
   * that
   *
   * @return
   */
  public static boolean isRCPronoun(String word) {
    for (String s : f_relativeClausePronouns) {
      if (s.equals(word)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param a
   * @return
   */
  public static boolean hasFrequencyAttached(Action a) {
    if (a.getPreAdvMod() != null) {
      return Constants.f_frequencyWords.contains(a.getPreAdvMod().toLowerCase());
    }
    return false;
  }

  public static boolean isBe(String word) {
    for (String s : f_beForms) {
      if (s.equals(word)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param value
   * @return
   */
  public static boolean canBePersonPronoun(String value) {
    if (value.equalsIgnoreCase("it")) {
      return false;
    }
    return isPersonalPronoun(value);
  }

  /**
   * @param value
   * @return
   */
  public static boolean canBeObjectPronoun(String value) {
    for (String s : f_inanimatePronouns) {
      if (s.equalsIgnoreCase(value)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param value
   * @return
   */
  public static boolean isPersonalPronoun(String value) {
    for (String s : f_personPronouns) {
      if (s.equalsIgnoreCase(value)) {
        return true;
      }
    }
    return false;
  }

  public static boolean isDeterminer(String value) {
    for (String s : f_determiner) {
      if (s.equalsIgnoreCase(value)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param value
   * @return
   */
  public static boolean is3rdPerson(String value) {
    for (String s : f_3rdpersonPronouns) {
      if (s.equalsIgnoreCase(value)) {
        return true;
      }
    }
    return false;
  }

  /**
   * @param name
   * @return
   */
  public static String get3rdPsing(String name) {
    WordNetFunctionality wnf = new WordNetFunctionality();
    String _base = wnf.getBaseForm(name);
    if (Constants.f_weakVerbTo3rdPSing.containsKey(_base)) {
      return Constants.f_weakVerbTo3rdPSing.get(_base);
    }
    if (_base.endsWith("s") || _base.endsWith("x")) {
      return _base + "es";
    }
    return _base + "s";
  }

  /**
   * needed due to bug in Tree.removeChild() for Treenodes
   *
   * @param copy
   * @param i
   * @return
   */
  public static Tree removeChild(Tree copy, int i) {
    List<Tree> children = copy.getChildrenAsList();
    Tree child = children.get(i);
    children.remove(i);
    copy.setChildren(children);
    return child;
  }

  /**
   * @param object
   * @return
   */
  public static boolean isUnrealActor(ExtractedObject object) {
    if (object instanceof Actor) {
      Actor _a = (Actor) object;
      return _a.isUnreal();
    }
    return false;
  }

  /**
   * @param object
   * @return
   */
  public static boolean isMetaActor(ExtractedObject object) {
    if (object instanceof Actor) {
      Actor _a = (Actor) object;
      return _a.isMetaActor();
    }
    return false;
  }

  /**
   * @param name
   * @return
   */
  public static boolean isActionResolutionDeterminer(String name) {
    for (String s : f_ActionResolutionDeterminer) {
      if (s.equalsIgnoreCase(name)) {
        return true;
      }
    }
    return false;
  }
}
