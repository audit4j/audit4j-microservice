package org.audit4j.microservice;

import java.io.IOException;

import org.audit4j.core.AuditManager;
import org.audit4j.core.exception.ConfigurationException;
import org.audit4j.core.exception.InitializationException;
import org.audit4j.microservice.core.ConfigurationManager;
import org.audit4j.microservice.core.Context;
import org.audit4j.microservice.core.Transport;

class ServerContext implements Context {

    private String serverConfigFilePath = "conf/server.config.yml";

    private String audit4jConfigFilePath = "conf/audit4j.conf.yml";

    private HTTPServer httpServer = new VertxServer();

    private ServerConfiguration config;

    @Override
    public void start() throws InitializationException {
        System.out.println("====================================================");
        System.out.println("========= Starting Audit4j Microservice... =========");
        System.out.println("====================================================");

        try {
            if (AppRunningChecker.checkIfAlreadyRunning()) {
                System.out.println("An instance is already running...!");
                System.exit(0);
            }
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Load Configurations
        ConfigurationManager manager = new ConfigurationManager(serverConfigFilePath);
        try {
            config = manager.loadConfiguration();
            System.out.println("CONF: " + config);
        } catch (ConfigurationException e) {
            throw new InitializationException("Could not load configuraiton.!", e);
        }

        // Initializing transports
        for (Transport transport : config.getTransports()) {
            transport.init();
        }

        // Initializing HTTP Server
        httpServer.init();

        // Initialize Audit4j Core
        AuditManager.startWithConfiguration(audit4jConfigFilePath);

        System.out.println("====================================================");
        System.out.println("========== Audit4j Microservice Started.! ==========");
        System.out.println("====================================================");
        System.out.println("type: 'exit' to shutdown server safetly.");
    }

    @Override
    public void stop() {
        for (Transport transport : config.getTransports()) {
            transport.stop();
        }

        httpServer.stop();

        AuditManager.shutdown();
        // AppRunningChecker.unlockFile();

        System.out.println("====================================================");
        System.out.println("===== Audit4j Microservice shutdown safetly.! ======");
        System.out.println("====================================================");
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
