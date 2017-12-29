package org.audit4j.microservice.core;

import org.audit4j.core.Initializable;
import org.audit4j.microservice.web.VertxServer;

public interface HTTPServer extends Initializable{

    void setPort(int port);
    
    public static HTTPServer create(int port) {
        return new VertxServer(port);
      }
}
