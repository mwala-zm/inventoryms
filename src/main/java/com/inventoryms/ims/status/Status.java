package com.inventoryms.ims.status;

public enum Status{
  COMPLETE("Complete"),
  RETURNED("Returned"),
  CANCELLED("Cancelled"),
  AVAILABLE("Available");

  private final String label;

  Status(String label){
    this.label = label;
  }

  public String getLabel(){
    return label;
  }

}
