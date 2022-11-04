package org.sebi.incident;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("rest/incidents")
public class IncidentResource {
    
    @GET
    public List<Incident> getIncidents(){
       return Incident.listAll();
    } 
}
