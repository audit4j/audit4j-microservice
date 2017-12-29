package org.audit4j.microservice;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        main.run(args);
    }

    public void run(String[] args) {
        ServerContext server = new ServerContext();
        server.start();

        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            String line = sc.nextLine();
            if ("exit".equals(line)) {
                server.stop();
                System.exit(0);
            }
        }
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.stop();
        }));
    }
}
