package org.audit4j.microservice;

import org.audit4j.core.dto.Event;

public interface TransportClient<T extends Event> {

	void send(T event);

}
