package org.audit4j.microservice.transport;

import java.util.concurrent.TimeUnit;

import org.audit4j.core.dto.AuditEvent;
import org.audit4j.core.dto.Event;
import org.audit4j.core.exception.HandlerException;
import org.audit4j.core.exception.InitializationException;
import org.audit4j.core.handler.Handler;
import org.nustaq.serialization.FSTConfiguration;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;

public class AuditServerHandler<E extends Event> extends Handler  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4367445636079624061L;
	private FSTConfiguration conf;
	private WebsocketHandler handler = null;
	private HttpClient client;
	private Vertx vertx;
	private Integer port = 9999;
	
	private String host = "localhost";

	@Override
	public void init() throws InitializationException {
		handler = new WebsocketHandler();
		handler.init();
		conf = FSTConfiguration.createDefaultConfiguration();
		vertx = Vertx.vertx();
		client = vertx.createHttpClient();
		client.websocket(port, host, "/audit", handler);
	}
	
	@Override
	public void handle() throws HandlerException {
		handler.sendBinary(conf.asByteArray(getAuditEvent()));
	}

	@Override
	public void stop() {
		handler.stop();
		client.close();
		vertx.close();
	}

	public static void main(String[] args) throws Exception {
		AuditServerHandler<AuditEvent> client = new AuditServerHandler<>();
		client.init();

		TimeUnit.SECONDS.sleep(5);
		for (int i = 0; i < 500; i++) {
			AuditEvent event = new AuditEvent();
			event.setClient("03a1c230-d1fa-11e5-9758-68f728daf525");
			event.setAction("asdsa" + i);
			event.setActor("asdas");
			client.setAuditEvent(event);
			client.handle();
		}
	}


	
}
