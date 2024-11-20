package org.sebi;

import java.util.List;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AntennaEncoder implements Encoder.Text<List<Antenna>> {
    ObjectMapper objectMapper = new ObjectMapper();


@Override
public void init(EndpointConfig config) {
    // TODO Auto-generated method stub
    
}

@Override
public void destroy() {
    // TODO Auto-generated method stub
    
}

@Override
public String encode(List<Antenna> object) throws EncodeException {
    String value = "[]";
    try {
       value = objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return value;
}

}
