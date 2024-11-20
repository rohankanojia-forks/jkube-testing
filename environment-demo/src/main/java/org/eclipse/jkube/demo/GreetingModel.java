package org.eclipse.jkube.demo;

public class GreetingModel {
  private String greeting;
  private String environment;

  public GreetingModel(String s, String e) {
    this.greeting = s;
    this.environment = e;
  }

  public String getGreeting() {
    return greeting;
  }

  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }

  public String getEnvironment() {
    return environment;
  }

  public void setEnvironment(String environment) {
    this.environment = environment;
  }
}
