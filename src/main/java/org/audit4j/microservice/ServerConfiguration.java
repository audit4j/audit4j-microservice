package org.audit4j.microservice;

public class ServerConfiguration extends Configuration {

	private String webSocketport;
	
	private String serverPort;

	public String getWebSocketport() {
		return webSocketport;
	}

	public void setWebSocketport(String webSocketport) {
		this.webSocketport = webSocketport;
	}

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
}
