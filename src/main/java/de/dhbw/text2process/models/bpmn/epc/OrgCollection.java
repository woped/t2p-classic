package de.dhbw.text2process.models.bpmn.epc;

import java.io.Serializable;

public class OrgCollection extends OrganisationCluster implements Serializable {

  /** Property if the Pool is a Black Box Pool */
  public static final String PROP_BLACKBOX_POOL = "blackbox_pool";
  /** Property if this Pool has multiple instances (0=FALSE, 1=TRUE) */
  public static final String PROP_MULTI_INSTANCE = "multi_instance";

  public OrgCollection() {
    super();
    init();
    setText("OrganisOrgCollection");
  }

  public OrgCollection(int x, int y, String label) {
    super();
    init();
    setText(label);
  }

  @Override
  protected void init() {
    super.init();
    setProperty(PROP_BLACKBOX_POOL, "FALSE");
    setProperty(PROP_MULTI_INSTANCE, "FALSE");
  }

  @Override
  public void setProperty(String key, String value) {
    super.setProperty(key, value);
  }

  @Override
  public boolean isCollapsed() {
    return "TRUE".equalsIgnoreCase(getProperty(PROP_BLACKBOX_POOL));
  }

  public String toString() {
    return "EPK OrgCollection (" + getName() + ")";
  }
}
