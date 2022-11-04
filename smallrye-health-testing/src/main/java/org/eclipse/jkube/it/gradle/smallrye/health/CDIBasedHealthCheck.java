package org.eclipse.jkube.it.gradle.smallrye.health;

import io.smallrye.health.SmallRyeHealth;
import io.smallrye.health.SmallRyeHealthReporter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CDIBasedHealthCheck", urlPatterns = "/health")
public class CDIBasedHealthCheck extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) {
    SmallRyeHealthReporter reporter = new SmallRyeHealthReporter();
    SmallRyeHealth health = reporter.getHealth();
    if (health.isDown()) {
      res.setStatus(503);
    }
    try {
      reporter.reportHealth(res.getOutputStream(), health);
    } catch (IOException ioe) {
      res.setStatus(500);
    }
  }
}
