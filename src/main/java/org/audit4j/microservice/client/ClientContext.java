package org.audit4j.microservice.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.audit4j.microservice.util.KeyGenerator;

class ClientContext {

	Map<String, Client> clients = new ConcurrentHashMap<>();

	KeyGenerator keyGenerator;

	public ClientContext() {
		keyGenerator = new KeyGenerator();
	}

	String registerClient() {
		String key = keyGenerator.generateRandomKey();
		clients.put(key, new Client());
		return key;
	}

	boolean containsClient(String clientKey) {
		Client client = clients.get(clientKey);
		if (client == null) {
			return false;
		} else {
			return true;
		}
	}

}
