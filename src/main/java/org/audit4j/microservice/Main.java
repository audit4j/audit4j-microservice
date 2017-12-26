package org.audit4j.microservice;

import java.util.Scanner;

public class Main {

    private static boolean shutdown = false;
    
	public static void main(String[] args) {
		main();
	}
	
	public static void main() {
		ServerContext server = new ServerContext();
		server.start();
		
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		if ("exit".equals(line)) {
		    server.stop();
		    shutdown = true;
		    System.exit(0);
        }
		
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
		    if (!shutdown) {
		        server.stop(); 
            }
		 }));
	}
}
