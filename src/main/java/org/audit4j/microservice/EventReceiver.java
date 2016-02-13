package org.audit4j.microservice;

import org.audit4j.core.dto.AuditEvent;
import org.audit4j.microservice.transport.Ack;

public interface EventReceiver {

	Ack receive(AuditEvent event);

}
