package org.audit4j.microservice;

import org.audit4j.core.exception.InitializationException;

public interface Context {

	void start() throws InitializationException;
	
	void stop();
	
	void enable();
	
	void disable();
	
	void terminate();
}
