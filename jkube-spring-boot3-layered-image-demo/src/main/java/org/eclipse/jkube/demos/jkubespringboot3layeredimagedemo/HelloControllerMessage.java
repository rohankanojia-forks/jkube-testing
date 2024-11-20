package org.eclipse.jkube.demos.jkubespringboot3layeredimagedemo;

public class HelloControllerMessage {
  private String message;

  public HelloControllerMessage(String msg) {
    this.message = msg;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
