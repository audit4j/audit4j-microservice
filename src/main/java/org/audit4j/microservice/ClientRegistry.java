package org.audit4j.microservice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.audit4j.core.Initializable;
import org.audit4j.core.exception.InitializationException;
import org.audit4j.microservice.util.KeyGenerator;

/**
 * The Class ClientContext.
 */
class ClientRegistry implements Initializable {

	/** The clients. */
	Map<String, Client> clients = new ConcurrentHashMap<>();

	/** The Constant CLIENT_DB_CONFIG_PATH. */
	private static final String CLIENT_DB_CONFIG_PATH = "clients.db";
	
	/** The key generator. */
	KeyGenerator keyGenerator;

	/**
	 * Instantiates a new client context.
	 */
	public ClientRegistry() {
		keyGenerator = new KeyGenerator();
	}

	/* (non-Javadoc)
	 * @see org.audit4j.core.Initializable#init()
	 */
	@Override
	public void init() throws InitializationException {
		try {
			List<String> lines = Files.readAllLines(Paths.get(CLIENT_DB_CONFIG_PATH));
			for (String entry : lines) {
				String[] splittedEntry = entry.split(":");
				clients.put(splittedEntry[0], new Client(splittedEntry[1]));
			}
		} catch (NoSuchFileException ne) {
			System.out.println("couldn't find the file");
		} catch (IOException e){
			throw new InitializationException("", e);
		}
	}

	/**
	 * Register client.
	 *
	 * @param cientName the cient name
	 * @return the string
	 */
	public String registerClient(String cientName) {
		String key = keyGenerator.generateRandomKey();
		clients.put(key, new Client(cientName));
		return key;
	}

	/**
	 * Unregister.
	 *
	 * @param key the key
	 * @return the string
	 */
	public String unregister(String key){
		return clients.remove(key).getName();
	}
	
	/**
	 * Contains client.
	 *
	 * @param clientKey the client key
	 * @return true, if successful
	 */
	public boolean containsClient(String clientKey) {
		Client client = clients.get(clientKey);
		if (client == null) {
			return false;
		} else {
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see org.audit4j.core.Initializable#stop()
	 */
	@Override
	public void stop() {
		List<String> lines = new ArrayList<>();
		for (String key : clients.keySet()) {
			lines.add(key + ":" + clients.get(key).getName());
		}
		try {
			Files.write(Paths.get(CLIENT_DB_CONFIG_PATH), lines);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
