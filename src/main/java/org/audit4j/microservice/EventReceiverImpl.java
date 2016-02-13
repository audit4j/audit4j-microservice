package org.audit4j.microservice;

import org.audit4j.core.AuditManager;
import org.audit4j.core.dto.AuditEvent;
import org.audit4j.microservice.transport.Ack;

public class EventReceiverImpl implements EventReceiver {

	private ClientRegistry clientContext = null;

	@Override
	public Ack receive(AuditEvent event) {
		// Validate Event
		if (clientContext.containsClient(event.getMeta().client)) {
			AuditManager.getInstance().audit(event);
			return Ack.SUCCESS();
		} else {
			return Ack.UNAUTHORIZED();
		}
	}

	public void setClientContext(ClientRegistry clientContext) {
		this.clientContext = clientContext;
	}
}
