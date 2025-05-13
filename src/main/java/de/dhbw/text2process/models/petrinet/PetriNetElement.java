package de.dhbw.text2process.models.petrinet;

import de.dhbw.text2process.processors.petrinet.IDHandler;

public abstract class PetriNetElement {

  private String originID;
  private String xmlString;
  protected String text;
  protected IDHandler idHandler;
  protected int IDCounter;
  protected String ID;

  public PetriNetElement(String originID, IDHandler idHandler) {
    this.idHandler = idHandler;
    this.originID = originID;
    IDCounter = this.idHandler.getNext();
  }

  public String getOriginID() {
    return originID;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getID() {
    return ID;
  }

  public abstract void generateXmlString();

  public String getXmlString() {
    this.generateXmlString();
    return xmlString;
  }

  public void setXmlString(String xmlString) {
    this.xmlString = xmlString;
  }

  @Override
  public String toString() {
    return "PetriNetElement{"
        + "originID='"
        + originID
        + '\''
        + ", xmlString='"
        + xmlString
        + '\''
        + ", text='"
        + text
        + '\''
        + ", idHandler="
        + idHandler
        + ", IDCounter="
        + IDCounter
        + ", ID='"
        + ID
        + '\''
        + '}';
  }
}
