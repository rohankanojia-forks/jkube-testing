package org.eclipse.jkube.demo;

public class GreetingService {
  public GreetingService() { }

  public GreetingModel greet() {
    String greetEnv = System.getenv("GREET_ENV");
    if (greetEnv == null) {
      greetEnv = "NOT_FOUND";
    }
    return new GreetingModel("hello", greetEnv);
  }
}
