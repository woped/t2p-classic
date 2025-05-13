package de.dhbw.text2process.models.bpmn;

import de.dhbw.text2process.models.bpmn.nodes.FlowObject;

public class Conversation extends FlowObject {

  /** Determines whether this Conversation is compound or not (1,0) */
  public static final String PROP_COMPOUND = "compound";
  /** Determines whether this Conversation is compound or not (1,0) */
  public static final String PROP_CALL = "call";

  public Conversation() {
    super();
  }

  public Conversation(int xPos, int yPos, String text) {
    super();
    setText(text);
  }

  public String toString() {
    return "BPMN conversation";
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
