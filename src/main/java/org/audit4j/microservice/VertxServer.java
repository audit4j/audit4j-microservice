package org.audit4j.microservice;

import org.audit4j.core.exception.InitializationException;
import org.audit4j.microservice.core.HTTPServer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class VertxServer extends AbstractVerticle implements HTTPServer{

    private ServerContext context;

    private int port = 8080;
    
    Vertx vertx;
    
    @Override
    public void start() {
        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());
        router.get("/health").handler(this::handlerHealth);
        
        router.post("/rest/event").handler(new RESTAuditEventHandler()::handleEvent);
              
        router.route("/*").handler(StaticHandler.create());

        vertx.createHttpServer().requestHandler(router::accept).listen(port);
    }

    private void handlerHealth(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json")
                .end(new JsonObject().put("status", "running").encodePrettily());
    }
    
    public static void main(String[] args) {
        VertxServer server = new VertxServer();
        server.vertx = Vertx.vertx();
        server.vertx.deployVerticle(server);
    }
    
    @Override
    public void init() throws InitializationException {
        vertx = Vertx.vertx();
        vertx.deployVerticle(this);
        System.out.println("Vertx HTTP server started.! port: " + port);
    }
    
    @Override
    public void stop() {
        vertx.close();
    }

    @Override
    public void setPort(int port) {
       this.port = port;
    }
}
