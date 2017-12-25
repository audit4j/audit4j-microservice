package org.audit4j.microservice;

import java.util.ArrayList;
import java.util.List;

import org.audit4j.microservice.core.Transport;
import org.audit4j.microservice.transport.thrift.ThriftTransportServer;

public class TransportRegistry {

    private List<Transport> transports = new ArrayList<>();

    public TransportRegistry() {
        transports.add(new ThriftTransportServer("localhost", 9091, false,
                "conf/trust/keystore.jks", "123456"));
    }

    public void addTranport(Transport transport) {
        transports.add(transport);
    }
    
    public List<Transport> getTransports() {
        return transports;
    }

}
