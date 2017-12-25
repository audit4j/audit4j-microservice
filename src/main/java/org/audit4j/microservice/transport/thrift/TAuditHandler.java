package org.audit4j.microservice.transport.thrift;

import org.apache.thrift.TException;
import org.audit4j.microservice.core.EventReceiver;

public class TAuditHandler implements TAuditService.Iface {

    private EventReceiver reciver;
    TAuditEventTransformer transformer;
    TAckTransformer ackTransformer;
    
    public TAuditHandler(EventReceiver reciver) {
        this.reciver = reciver;
        this.transformer = new TAuditEventTransformer();
        this.ackTransformer = new TAckTransformer();
    }

    @Override
    public TAck audit(TAuditEvent event) throws TException {
        return ackTransformer.transformFromAck(reciver.receive(transformer.transformToEvent(event)));
    }

}
