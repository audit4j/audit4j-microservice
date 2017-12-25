/*
 * 
 */
package org.audit4j.microservice.core;

import java.util.Map;

/**
 * The Class Configuration.
 */
public class Configuration {
    
    /** The properties. */
    private Map<String, String> properties;

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

    @Override
    public String toString() {
        return "Configuration [properties=" + properties + "]";
    }
}
