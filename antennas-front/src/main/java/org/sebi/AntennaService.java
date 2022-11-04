package org.sebi;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AntennaService {
    
    public List<Antenna> getAntennas(){
        Antenna antenna = new Antenna(46.928881, 2.409900);
        Antenna antenna2 = new Antenna(44.938881, 1.419900);
        Antenna antenna3 = new Antenna(45.2, 2.2900);
        return List.of(antenna,antenna2,antenna3);
    }
}
