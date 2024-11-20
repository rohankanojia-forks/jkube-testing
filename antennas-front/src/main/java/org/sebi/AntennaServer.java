package org.sebi;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(encoders = AntennaEncoder.class, value = "/antennas")         
@ApplicationScoped
public class AntennaServer {

    @Inject
    AntennaService antennaService;
   
    @OnOpen
    public void onOpen(Session session) {
        session.getAsyncRemote().sendObject(antennaService.getAntennas(), result ->  {
            if (result.getException() != null) {
                System.out.println("Unable to send message: " + result.getException());
            }
        });
        System.out.println("Got a connection");
    }

}
