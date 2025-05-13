/** modified taken from https://github.com/FabianFriedrich/Text2Process */
package de.dhbw.text2process.models.orgChart;

import de.dhbw.text2process.models.bpmn.nodes.ProcessEdge;
import de.dhbw.text2process.models.bpmn.nodes.ProcessNode;
import de.dhbw.text2process.models.meta.ProcessModel;
import java.util.LinkedList;
import java.util.List;

public class OrgChartModel extends ProcessModel {

  public OrgChartModel() {
    this(null);
  }

  public OrgChartModel(String name) {
    super(name);
    processUtils = new OrgChartUtils();
  }

  @Override
  public String getDescription() {
    return "Organizational Chart";
  }

  @Override
  public List<Class<? extends ProcessEdge>> getSupportedEdgeClasses() {
    List<Class<? extends ProcessEdge>> result = new LinkedList<Class<? extends ProcessEdge>>();
    result.add(Connection.class);
    return result;
  }

  @Override
  public List<Class<? extends ProcessNode>> getSupportedNodeClasses() {
    List<Class<? extends ProcessNode>> result = new LinkedList<Class<? extends ProcessNode>>();
    result.add(OrgUnit.class);
    result.add(Role.class);
    result.add(ManagerialRole.class);
    result.add(Substitute.class);
    result.add(Person.class);
    return result;
  }
}
