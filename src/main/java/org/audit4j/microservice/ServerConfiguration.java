package org.audit4j.microservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.audit4j.microservice.core.Configuration;
import org.audit4j.microservice.core.Transport;

public class ServerConfiguration extends Configuration {

    private List<Transport> transports = new ArrayList<>();
    
    public List<Transport> getTransports() {
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }

    @Override
    public String toString() {
        return "ServerConfiguration [transports=" + transports + "]";
    }
}
