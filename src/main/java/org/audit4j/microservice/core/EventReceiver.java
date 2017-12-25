package org.audit4j.microservice.core;

import org.audit4j.core.dto.AuditEvent;

public interface EventReceiver {

	Ack receive(AuditEvent event);

}
