/*
 * 
 */
package org.audit4j.microservice;

import java.util.Map;

/**
 * The Class Configuration.
 */
public class Configuration {

	/** The transport port. */
	private Integer transportPort;
	
    /** The properties. */
    private Map<String, String> properties;

	/**
	 * Gets the transport port.
	 *
	 * @return the transport port
	 */
	public Integer getTransportPort() {
		return transportPort;
	}

	/**
	 * Sets the transport port.
	 *
	 * @param transportPort the new transport port
	 */
	public void setTransportPort(Integer transportPort) {
		this.transportPort = transportPort;
	}

	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	public Map<String, String> getProperties() {
		return properties;
	}

	/**
	 * Sets the properties.
	 *
	 * @param properties the properties
	 */
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
}
