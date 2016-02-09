package org.audit4j.microservice.util;

import com.eaio.uuid.UUID;

public class KeyGenerator {

	public String generateRandomKey(){
		UUID uuid = new UUID();
		return uuid.toString();
	}
}
