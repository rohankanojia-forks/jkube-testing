package org.eclipse.jkube.quickstarts.maven.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("hello")
public class HelloWorldEndpoint {
  @GET
  @Produces("text/plain")
  public Response doGet() {
    return Response.ok("Hello, World.").build();
  }
}
