package org.eclipse.jkube.demos;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class MultiPartBody {
  @FormParam("file")
  @PartType(MediaType.APPLICATION_OCTET_STREAM)
  public InputStream file;

  @FormParam("fileName")
  @PartType(MediaType.TEXT_PLAIN)
  public String fileName;
}
