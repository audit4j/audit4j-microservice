package org.audit4j.microservice.transport;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;

import org.audit4j.core.dto.AuditEvent;
import org.audit4j.core.exception.InitializationException;
import org.nustaq.serialization.FSTConfiguration;


public class SocketServer implements TransportServer {

	@Override
	public void init() throws InitializationException {


		try {
			start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();

		/*
		 * HttpServer httpServer = httpServerBuilder().setPort(6060).build();
		 * 
		 * 
		 * // httpServer.setWebSocketOnOpenConsumer(webSocket ->
		 * webSocket.setTextMessageConsumer(message -> { //
		 * System.out.println("------------------"); //
		 * webSocket.sendText("ECHO " + message); // }));
		 * 
		 * httpServer.setWebSocketOnOpenConsumer(webSocket -> {
		 * webSocket.setBinaryMessageConsumer(message -> {
		 * System.out.println("----------------"); AuditEvent event=
		 * (AuditEvent) conf.asObject(message);
		 * 
		 * System.out.println(event.getActor());
		 * System.out.println("----------------");
		 * webSocket.sendBinary(conf.asByteArray(Ack.createSuccess()));
		 * 
		 * }); });
		 * 
		 * httpServer.start();
		 */
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	
	public void start() throws Exception {
		FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
		//VertxOptions options = new VertxOptions();
		Vertx vertx = Vertx.vertx();
		NetServer server = vertx.createNetServer();
		
		server.connectHandler(sock -> {

			sock.handler(buffer -> {
				byte[] buff =  buffer.getBytes();
				 AuditEvent auditEvent= (AuditEvent) conf.asObject(buff);
				 System.out.println("-------------");
				 System.out.println(auditEvent.getActor());
				 
				 //ws.writeBinaryMessage(Buffer.buffer(conf.asByteArray(Ack.SUCCESS())));
				 
		        });

			sock.write(Buffer.buffer(conf.asByteArray(Ack.SUCCESS())));
			//sock.close();
		    }).listen(9999);
	}

	public static void main(String[] args) {
		SocketServer server = new SocketServer();
		server.init();
	}
}
