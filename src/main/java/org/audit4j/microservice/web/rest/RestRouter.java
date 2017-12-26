package org.audit4j.microservice.web.rest;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class RestRouter {

    Vertx vertx;

    public RestRouter(Vertx vertx) {
        this.vertx = vertx;
    }

    public Router getRouter() {
        Router router = Router.router(vertx);

        router.get("/health").handler(this::handlerHealth);
        router.post("/rest/event").handler(new RESTAuditEventHandler()::handleEvent);

        return router;
    }

    private void handlerHealth(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json")
                .end(new JsonObject().put("status", "running").encodePrettily());
    }
}
