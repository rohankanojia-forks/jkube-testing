package org.eclipse.jkube.it.gradle.smallrye.health;

import io.smallrye.health.SmallRyeHealth;
import io.smallrye.health.SmallRyeHealthReporter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CDIBasedLivenessCheck", urlPatterns = "/health/live")
public class CDIBasedLivenessCheck extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
    SmallRyeHealthReporter reporter = new SmallRyeHealthReporter();
    SmallRyeHealth health = reporter.getLiveness();
    if (health.isDown()) {
      resp.setStatus(503);
    }
    try {
      reporter.reportHealth(resp.getOutputStream(), health);
    } catch (IOException ioe) {
      resp.setStatus(500);
    }
  }
}
