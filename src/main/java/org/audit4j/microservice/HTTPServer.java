package org.audit4j.microservice;

import org.audit4j.core.Initializable;

public interface HTTPServer extends Initializable{

    void setPort(int port);
}
