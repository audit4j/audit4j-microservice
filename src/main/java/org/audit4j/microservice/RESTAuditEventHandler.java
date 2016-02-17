package org.audit4j.microservice;

import org.audit4j.core.dto.AuditEvent;
import org.audit4j.microservice.transport.Ack;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class RESTAuditEventHandler implements Handler<RoutingContext> {

	private EventReceiver reciever = new EventReceiverImpl();

	@Override
	public void handle(RoutingContext routingContext) {
		JsonObject jsonEvent = routingContext.getBodyAsJson();
		AuditEvent event = Json.decodeValue(jsonEvent.encode(), AuditEvent.class);
		reciever.receive(event);

		HttpServerResponse response = routingContext.response();
		response.putHeader("content-type", "application/json")
				.end(new JsonObject(Json.encode(Ack.SUCCESS())).encodePrettily());
	}

}
