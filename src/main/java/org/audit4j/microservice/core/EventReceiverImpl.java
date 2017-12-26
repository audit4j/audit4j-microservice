package org.audit4j.microservice.core;

import org.audit4j.core.AuditManager;
import org.audit4j.core.dto.AuditEvent;

public class EventReceiverImpl implements EventReceiver {
	@Override
	public Ack receive(AuditEvent event) {
		/*// Validate Event
		if (clientContext.containsClient(event.getMeta().client)) {
			AuditManager.getInstance().audit(event);
			return Ack.SUCCESS();
		} else {
			return Ack.UNAUTHORIZED();
		}*/
		
		AuditManager.getInstance().audit(event);
        return Ack.SUCCESS();
	}
}
