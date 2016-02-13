package org.audit4j.microservice.transport;

import org.audit4j.core.Initializable;
import org.audit4j.core.dto.AuditEvent;
import org.audit4j.microservice.EventReceiver;
import org.audit4j.microservice.EventReceiverImpl;

public abstract class Transport implements Initializable{

	EventReceiver reciever = new EventReceiverImpl();
	
	public void receive(AuditEvent event){
		reciever.receive(event);
	}
}
