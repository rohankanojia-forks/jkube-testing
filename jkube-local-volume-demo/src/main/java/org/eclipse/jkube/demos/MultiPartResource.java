package org.eclipse.jkube.demos;

import io.quarkus.logging.Log;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Path("/client")
public class MultiPartResource {

  @POST
  @Path("/multipart")
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.TEXT_PLAIN)
  public Response upload(@MultipartForm MultiPartBody body) {
    Log.info("upload() for " + body.fileName);
    Log.info("file : " + body.file);
    Log.info("fileName : " + body.fileName);
    File uploadDir = new File(System.getProperty("user.dir"), "uploads");
    if (!uploadDir.exists() && !uploadDir.mkdirs()) {
      Log.error("Failure in creating upload dir: " + uploadDir.getAbsolutePath());
      return Response.serverError().build();
    }
    File targetFile = new File(uploadDir, body.fileName);
    try {
      Files.write(targetFile.toPath(), body.file.readAllBytes());
    } catch (IOException e) {
      Log.error("Error in writing file :" + targetFile.getAbsolutePath(), e);
      return Response.serverError().build();
    }

    Log.info("SUCCESS. File uploaded to " + targetFile.getAbsolutePath());
    return Response.ok().build();
  }
}
