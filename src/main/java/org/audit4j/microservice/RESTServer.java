package org.audit4j.microservice;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class RESTServer extends AbstractVerticle {

	@Override
	public void start() {
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		router.get("/health").handler(this::handlerHealth);
		router.get("/rest/event").handler(new RESTAuditEventHandler());

		vertx.createHttpServer().requestHandler(router::accept).listen(8080);
	}

	private void handlerHealth(RoutingContext routingContext) {
		HttpServerResponse response = routingContext.response();
		response.putHeader("content-type", "application/json")
				.end(new JsonObject().put("status", "running").encodePrettily());
	}
	
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new RESTServer());
	}
}
