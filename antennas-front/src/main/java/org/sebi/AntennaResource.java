package org.sebi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("rest")
public class AntennaResource {

    @ConfigProperty(name = "ping", defaultValue = "ping")
    String ping;

    @GET
    @Path("ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping(){
        return ping;
    }
}
