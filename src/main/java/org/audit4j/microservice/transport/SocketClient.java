package org.audit4j.microservice.transport;

import java.util.concurrent.TimeUnit;

import org.audit4j.core.Initializable;
import org.audit4j.core.dto.AuditEvent;
import org.audit4j.core.exception.InitializationException;
import org.nustaq.serialization.FSTConfiguration;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;

public class SocketClient implements Initializable {

	/*
	 * WebSocket webSocket; HttpClient httpClient;
	 */

	private NetSocket socket;

	//private FSTConfiguration conf;

	@Override
	public void init() throws InitializationException {

		try {
			start();
		} catch (Exception e) {
			throw new InitializationException("", e);
		}
		/*
		 * httpClient =
		 * HttpClientBuilder.httpClientBuilder().setHost("localhost")
		 * .setPort(6060).build(); httpClient.startClient();
		 * 
		 * 
		 * webSocket = httpClient .createWebSocket("/websocket/rocket");
		 * 
		 * 
		 * 
		 * // webSocket.setTextMessageConsumer(System.out::println); //
		 * webSocket.openAndWait();
		 * 
		 * webSocket.setBinaryMessageConsumer(message -> { Ack ack = (Ack)
		 * conf.asObject(message); System.out.println(ack.getMessage()); });
		 * webSocket.openAndWait();
		 * 
		 * Send some messages. //webSocket.sendText("Hi mom");
		 * //webSocket.sendText("Hello World!"); AuditEvent event = new
		 * AuditEvent(); event.setAction("asdsa"); event.setActor("asdas");
		 * 
		 * byte eventBytes[] = conf.asByteArray(event);
		 * 
		 * webSocket.sendBinary(eventBytes);
		 * 
		 * Sys.sleep(1000);
		 * 
		 * Setup the text consumer. webSocket.setBinaryMessageConsumer(message
		 * -> { System.out.println(message); }); webSocket.openAndWait();
		 * 
		 * Send some messages. AuditEvent event = new AuditEvent();
		 * event.setAction("asdsa"); event.setActor("asdas");
		 * 
		 * byte eventBytes[] = conf.asByteArray(event);
		 * 
		 * webSocket.sendBinary(eventBytes);
		 * 
		 * System.out.println("send"); Sys.sleep(1000);
		 */

	}

	public void start() throws Exception {
		FSTConfiguration conf  = FSTConfiguration.createDefaultConfiguration();

		Vertx vertx = Vertx.vertx();
		vertx.createNetClient().connect(9999, "localhost", res -> {

			if (res.succeeded()) {
				socket = res.result();
				socket.handler(buffer -> {
					Ack ack = (Ack) conf.asObject(buffer.getBytes());
					System.out.println(ack.getMessage());
					//socket.close();
				});

				for (int i = 0; i < 20; i++) {
					AuditEvent event = new AuditEvent();
					event.setAction("asdsa");
					event.setActor("asdas");
					byte eventBytes[] = conf.asByteArray(event);
					socket.write(Buffer.buffer(eventBytes));
					System.out.println("sent");
					//TimeUnit.SECONDS.sleep(1);
				}
				
				/*
				 * AuditEvent event = new AuditEvent();
				 * event.setAction("asdsa"); event.setActor("asdas");
				 * 
				 * byte eventBytes[] = conf.asByteArray(event);
				 * socket.write(Buffer.buffer(eventBytes));
				 * socket.write(Buffer.buffer(eventBytes));
				 * socket.write(Buffer.buffer(eventBytes));
				 */

			} else {
				System.out.println("Failed to connect " + res.cause());
			}
		});

		// HttpClient client = vertx.createHttpClient();

		// WebSocket websocket;

		/*
		 * client.websocket( 8080, "localhost", "/some-uri", new
		 * WebsocketHandler() );
		 */
		/*
		 * websocket -> { websocket.handler(data -> { Ack ack = (Ack)
		 * conf.asObject(data.getBytes()); System.out.println(ack.getMessage());
		 * }); AuditEvent event = new AuditEvent(); event.setAction("asdsa");
		 * event.setActor("asdas");
		 * 
		 * byte eventBytes[] = conf.asByteArray(event);
		 * websocket.writeBinaryMessage(Buffer.buffer(eventBytes)); }
		 */
	}

	public void send(AuditEvent event) {
		System.out.println("Sending....");
		/*byte eventBytes[] = conf.asByteArray(event);
		socket.write(Buffer.buffer(eventBytes));*/
	}

	@Override
	public void stop() {
		/*
		 * webSocket.close(); httpClient.stop();
		 */
	}

	public static void main(String[] args) throws Exception {
		SocketClient client = new SocketClient();
		client.start();

		TimeUnit.SECONDS.sleep(5);

	/*	for (int i = 0; i < 10; i++) {
			AuditEvent event = new AuditEvent();
			event.setAction("asdsa");
			event.setActor("asdas");
			client.send(event);
			TimeUnit.SECONDS.sleep(1);
		}
		*/
		
		TimeUnit.SECONDS.sleep(20);
	}

}
