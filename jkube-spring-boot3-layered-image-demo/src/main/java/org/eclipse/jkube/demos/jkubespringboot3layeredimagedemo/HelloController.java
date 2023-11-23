package org.eclipse.jkube.demos.jkubespringboot3layeredimagedemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  @GetMapping(value = "/", produces = "application/json")
  public HelloControllerMessage greet() {
    return new HelloControllerMessage("Hello World v2");
  }
}
