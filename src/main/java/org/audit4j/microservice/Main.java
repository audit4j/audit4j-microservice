package org.audit4j.microservice;

public class Main {

	public static void main(String[] args) {
		main();
	}
	
	public static void main() {
		Context server = new ServerContext();
		server.start();
	}
}
