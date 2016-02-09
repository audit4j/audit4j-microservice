package org.audit4j.microservice;

import org.audit4j.core.AuditManager;
import org.audit4j.core.exception.ConfigurationException;
import org.audit4j.core.exception.InitializationException;
import org.audit4j.microservice.transport.TransportServer;
import org.audit4j.microservice.transport.WebSocketServer;

public class ServerContext implements Context{

	private TransportServer server;
	
	@Override
	public void start() throws InitializationException {
		
		//Load Configurations
		ConfigurationManager manager = new ConfigurationManager();
		Configuration config;
		try {
			config =  manager.loadConfiguration();
			System.out.println(config);
		} catch (ConfigurationException e) {
			throw new InitializationException("Could not load configuraiton.!");
		}
		
		
		//Initializing transports
		WebSocketServer socketServer =  new WebSocketServer();
		socketServer.setPort(9999);
		
		server = socketServer;
		server.init();
		
		//Initialize Audit4j Core
		AuditManager.getInstance();
		
		
	}

	@Override
	public void stop() {
		server.stop();
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
