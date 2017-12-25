package org.audit4j.microservice;

import org.audit4j.core.AuditManager;
import org.audit4j.core.exception.ConfigurationException;
import org.audit4j.core.exception.InitializationException;
import org.audit4j.microservice.core.ClientRegistry;
import org.audit4j.microservice.core.ConfigurationManager;
import org.audit4j.microservice.core.Context;
import org.audit4j.microservice.core.Transport;

class ServerContext implements Context {

    private ClientRegistry clientRegistry;

    private TransportRegistry transportRegistry;

    private ServerConfiguration config;

    @Override
    public void start() throws InitializationException {
        System.out.println("====================================================");
        System.out.println("====+==== Starting Audit4j Microservice... =========");
        System.out.println("====================================================");
        // Load Configurations
        ConfigurationManager manager = new ConfigurationManager();
        try {
            config = manager.loadConfiguration();
            System.out.println("CONF: " + config);
        } catch (ConfigurationException e) {
            throw new InitializationException("Could not load configuraiton.!", e);
        }

        for (Transport transport : config.getTransports()) {
            transport.init();
        }

        // Initialize ClientContext
        clientRegistry = new ClientRegistry();
        clientRegistry.init();

        // Initialize Audit4j Core
        AuditManager.getInstance();

        System.out.println("====================================================");
        System.out.println("========== Audit4j Microservice Started.! ==========");
        System.out.println("====================================================");
        System.out.println("type: 'exit' to shutdown server safetly.");
    }

    ClientRegistry getClientRegistry() {
        return clientRegistry;
    }

    @Override
    public void stop() {
        for (Transport transport : config.getTransports()) {
            transport.stop();
        }
        clientRegistry.stop();
        AuditManager.shutdown();
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
