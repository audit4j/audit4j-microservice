package org.audit4j.microservice.core;

import org.audit4j.core.Initializable;

public interface HTTPServer extends Initializable{

    void setPort(int port);
}
