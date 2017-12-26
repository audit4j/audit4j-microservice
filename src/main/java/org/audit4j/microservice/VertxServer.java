package org.audit4j.microservice;

import org.audit4j.core.exception.InitializationException;
import org.audit4j.microservice.core.HTTPServer;
import org.audit4j.microservice.web.WebRouter;
import org.audit4j.microservice.web.rest.RestRouter;

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
        WebRouter webRouter = new WebRouter(vertx);
        Router router = webRouter.getRouter();

        RestRouter restRouter = new RestRouter(vertx);
        router.mountSubRouter("/api", restRouter.getRouter());
        

        router.route("/*").handler(StaticHandler.create());
        
        vertx.createHttpServer().requestHandler(router::accept).listen(port);
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
