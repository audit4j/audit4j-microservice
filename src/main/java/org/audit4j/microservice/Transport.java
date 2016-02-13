package org.audit4j.microservice;

import java.util.Map;

import org.audit4j.core.Initializable;
import org.audit4j.core.dto.AuditEvent;

/**
 * The Class Transport.
 */
public abstract class Transport implements Initializable {

	/** The reciever. */
	EventReceiver reciever = new EventReceiverImpl();
	
	/** The properties. */
	private Map<String, String> properties;

	/**
	 * Receive.
	 *
	 * @param event the event
	 */
	public void receive(AuditEvent event) {
		reciever.receive(event);
	}

	/**
	 * Gets the property.
	 *
	 * @param key the key
	 * @return the property
	 */
	public String getProperty(String key) {
        return properties.get(key);
    }
	
	/**
	 * Sets the properties.
	 *
	 * @param properties the properties
	 */
	void setProperties(final Map<String, String> properties) {
		this.properties = properties;
	}
}
