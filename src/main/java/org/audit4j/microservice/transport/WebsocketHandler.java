package org.audit4j.microservice.transport;

import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.WebSocket;

import org.audit4j.core.Initializable;
import org.audit4j.core.exception.InitializationException;
import org.nustaq.serialization.FSTConfiguration;

public class WebsocketHandler implements Handler<WebSocket>, Initializable{
	FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
	
	WebSocket websocket;
	
	@Override
	public void handle(WebSocket websocket) {
		this.websocket = websocket;
		websocket.handler(data -> {
			Ack ack = (Ack) conf.asObject(data.getBytes());
			System.out.println(ack.getMessage());
		});
		

		//byte eventBytes[] = conf.asByteArray(event);
		//websocket.writeBinaryMessage(Buffer.buffer(eventBytes));
		//websocket.close();
	}
	
	public void sendBinary(byte[] data){
		websocket.writeBinaryMessage(Buffer.buffer(data));
	}

	@Override
	public void init() throws InitializationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		websocket.close();
	}
}
