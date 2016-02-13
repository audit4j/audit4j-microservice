package org.audit4j.microservice;

import java.util.HashMap;
import java.util.Map;

import org.audit4j.core.AuditManager;
import org.audit4j.core.exception.ConfigurationException;
import org.audit4j.core.exception.InitializationException;

class ServerContext implements Context {

	private ClientRegistry clientRegistry;

	private TransportRegistry transportRegistry;

	private Configuration config;

	@Override
	public void start() throws InitializationException {

		// Load Configurations
		ConfigurationManager manager = new ConfigurationManager();
		try {
			config = manager.loadConfiguration();
			System.out.println(config);
		} catch (ConfigurationException e) {
			throw new InitializationException("Could not load configuraiton.!", e);
		}

		// Initializing transports
		transportRegistry = new TransportRegistry();
		for (Transport transport : transportRegistry.getTransports()) {
			Map<String, String> transportProperties = new HashMap<>();
			transportProperties.putAll(config.getProperties());
			transport.setProperties(transportProperties);
			transport.init();
		}

		// Initialize ClientContext
		clientRegistry = new ClientRegistry();
		clientRegistry.init();

		// Initialize Audit4j Core
		AuditManager.getInstance();
	}

	public ClientRegistry getClientRegistry() {
		return clientRegistry;
	}

	@Override
	public void stop() {
		for (Transport transport : transportRegistry.getTransports()) {
			transport.stop();
		}
		clientRegistry.stop();
		AuditManager.shutdown();
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void terminate() {
		// TODO Auto-generated method stub

	}

}
