package de.dhbw.text2process.models.bpmn;

import de.dhbw.text2process.models.bpmn.nodes.ProcessNode;
import java.util.LinkedList;
import java.util.List;

public class StartEvent extends Event {

  /** The interruption type of the event. Possible values are "0" or "Throwing" * */
  public static final String PROP_NON_INTERRUPTING = "non_interupting";

  public static final String EVENT_NON_INTERRUPTING_FALSE = "0";
  public static final String EVENT_NON_INTERRUPTING_TRUE = "1";

  public StartEvent() {
    super();
    initializeProperties();
  }

  public StartEvent(String label) {
    super();
    setText(label);
    initializeProperties();
  }

  protected void initializeProperties() {
    setProperty(PROP_NON_INTERRUPTING, EVENT_NON_INTERRUPTING_FALSE);
    // setPropertyEditor(PROP_NON_INTERRUPTING, new BooleanPropertyEditor());
  }

  @Override
  public List<Class<? extends ProcessNode>> getVariants() {
    List<Class<? extends ProcessNode>> result = new LinkedList<Class<? extends ProcessNode>>();
    result.add(StartEvent.class);
    // result.add(MessageStartEvent.class);
    // result.add(TimerStartEvent.class);
    // result.add(ErrorStartEvent.class);
    // result.add(EscalationStartEvent.class);
    // result.add(CompensationStartEvent.class);
    // result.add(ConditionalStartEvent.class);
    // result.add(SignalStartEvent.class);
    // result.add(MultipleStartEvent.class);
    // result.add(ParallelMultipleStartEvent.class);
    return result;
  }

  public String toString() {
    return "Start Event (" + getText() + ")";
  }

  @Override
  public void setIncoming(SequenceFlow flow) {
    throw new IllegalArgumentException("not implemented yet!");
  }

  @Override
  public void setOutgoing(SequenceFlow flow) {
    throw new IllegalArgumentException("not implemented yet!");
  }
}
