package org.demo.eclipse.eclipsejkubedemoprojectgradle;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JKubeDemoApplication {
  @GetMapping("/")
  public ResponseEntity<String> greet() {
    return ResponseEntity.ok("Hello JKube!");
  }
}