package org.audit4j.microservice;

public class Main {

	public static void main(String[] args) {
		main();
	}
	
	public static void main() {
		ServerContext server = new ServerContext();
		server.start();
		System.out.println(server.getClientRegistry().registerClient("asdsadd"));
	//	server.stop();
	}
}
