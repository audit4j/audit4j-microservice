package org.audit4j.microservice;

import org.audit4j.microservice.core.ClientRegistry;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class RESTClientHandler {

    private ClientRegistry registry;

    RESTClientHandler(ClientRegistry registry) {
        this.registry = registry;
    }

    public void registorClient(RoutingContext routingContext) {
        String clientName = routingContext.request().getParam("clientName");
        HttpServerResponse response = routingContext.response();

        String token = registry.registerClient(clientName);
        response.putHeader("content-type", "application/json")
                .end(new JsonObject().put("token", token).encodePrettily());
    }

    public void unregistorClient(RoutingContext routingContext) {
        String key = routingContext.request().getParam("key");
        HttpServerResponse response = routingContext.response();

        String clientName = registry.unregister(key);
        response.putHeader("content-type", "application/json")
                .end(new JsonObject().put("client", clientName)
                        .put("message", "Client Un Registered successfully").encodePrettily());
    }
}
