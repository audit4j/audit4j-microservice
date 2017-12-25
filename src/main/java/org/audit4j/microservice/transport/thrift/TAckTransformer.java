package org.audit4j.microservice.transport.thrift;

import org.audit4j.microservice.core.Ack;

public class TAckTransformer {

    public Ack transformToAck(TAck ack) {
        Ack auditAck = new Ack();
        auditAck.setCode(ack.getCode());
        auditAck.setMessage(ack.getMessage());
        return auditAck;
    }
    
    public TAck transformFromAck(Ack auditAck) {
        TAck ack = new TAck();
        ack.setCode(auditAck.getCode());
        ack.setMessage(auditAck.getMessage());
        return ack;
    }
}
