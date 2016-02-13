package org.audit4j.microservice.transport;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;

import org.audit4j.core.AuditManager;
import org.audit4j.core.dto.AuditEvent;
import org.audit4j.core.exception.InitializationException;
import org.audit4j.core.util.Log;
import org.audit4j.microservice.EventReceiver;
import org.audit4j.microservice.Transport;

public class WebSocketTransportServer extends Transport {

	Serializer serializer;

	private int port = 9999;

	@Override
	public void init() throws InitializationException {

		serializer = new SerializerImpl();

		// VertxOptions options = new VertxOptions();
		Vertx vertx = Vertx.vertx();

		vertx.createHttpServer()
				.websocketHandler(
						ws -> ws.handler(buffer -> {
							ws.frameHandler(event -> {
								if (event.isFinal()) {
									if (event.isBinary()) {
										byte[] buff = buffer.getBytes();
										try {
											AuditEvent auditEvent = serializer
													.fromByteArray(buff,
															AuditEvent.class);

											Log.info("Recived Message..");
											ws.writeBinaryMessage(Buffer.buffer(serializer
													.toByteArray(Ack.SUCCESS())));
											receive(auditEvent);
										} catch (Exception e) {
											ws.writeBinaryMessage(Buffer
													.buffer(serializer
															.toByteArray(Ack
																	.FAIL())));
										}

									} else if (event.isText()) {
										AuditEvent auditEvent = serializer.fromJson(
												buffer.toString(),
												AuditEvent.class);
										Log.info("Recived Message..");
										ws.writeFinalTextFrame("success");
										receive(auditEvent);
									}
								}

							});
						})).listen(port);

	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setSerializer(Serializer serializer) {
		this.serializer = serializer;
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		WebSocketTransportServer server = new WebSocketTransportServer();
		server.init();
	}
}
