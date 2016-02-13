package org.audit4j.microservice;

import java.util.ArrayList;
import java.util.List;

import org.audit4j.microservice.transport.WebSocketTransportServer;

public class TransportRegistry {

	private List<Transport> transports = new ArrayList<>();

	public TransportRegistry(){
		transports.add(new WebSocketTransportServer());
	}
	
	public List<Transport> getTransports() {
		return transports;
	}
	
}
