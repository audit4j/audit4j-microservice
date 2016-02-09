package org.audit4j.microservice.transport;

import java.util.concurrent.TimeUnit;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;

import org.audit4j.core.Initializable;
import org.audit4j.core.dto.AuditEvent;
import org.audit4j.core.dto.Event;
import org.audit4j.core.exception.InitializationException;
import org.audit4j.microservice.TransportClient;
import org.nustaq.serialization.FSTConfiguration;

public class WebSocketClient<E extends Event> implements Initializable,
		TransportClient<E> {

	FSTConfiguration conf;
	WebsocketHandler handler = null;
	HttpClient client;
	Vertx vertx;

	@Override
	public void init() throws InitializationException {

		handler = new WebsocketHandler();
		handler.init();

		conf = FSTConfiguration.createDefaultConfiguration();

		vertx = Vertx.vertx();
		client = vertx.createHttpClient();

		client.websocket(9999, "localhost", "/audit", handler);
	}

	@Override
	public void send(E event) {
		handler.sendBinary(conf.asByteArray(event));
	}

	@Override
	public void stop() {
		handler.stop();
		client.close();
		vertx.close();
	}

	public static void main(String[] args) throws Exception {
		WebSocketClient<AuditEvent> client = new WebSocketClient<>();
		client.init();

		TimeUnit.SECONDS.sleep(5);
		for (int i = 0; i < 500; i++) {
			AuditEvent event = new AuditEvent();
			event.setAction("asdsa" + i);
			event.setActor("asdas");
			client.send(event);
		}
	}
}
