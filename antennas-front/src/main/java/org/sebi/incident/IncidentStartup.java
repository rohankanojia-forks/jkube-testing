package org.sebi.incident;

import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class IncidentStartup {
    
    @Transactional
    void onStart(@Observes StartupEvent ev) {
        Incident incident = new Incident();
        incident.date = new Date();
        incident.description = "coupure fibre";
        incident.status = false;
        incident.persist();

        Incident incident1 = new Incident();
        incident1.date = new Date();
        incident1.description = "Panne émetteur";
        incident1.status = true;
        incident1.persist();
    }

}
