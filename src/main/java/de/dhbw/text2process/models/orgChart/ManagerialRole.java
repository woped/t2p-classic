/** modified taken from https://github.com/FabianFriedrich/Text2Process */
package de.dhbw.text2process.models.orgChart;

import de.dhbw.text2process.models.bpmn.SequenceFlow;

public class ManagerialRole extends OrgChartElement {

  /** */
  public ManagerialRole() {
    super();
    setHasLine(true);
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
