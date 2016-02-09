package org.audit4j.microservice.transport;

import org.nustaq.serialization.FSTConfiguration;

import com.google.gson.Gson;

/**
 * Custom Serialization Wrapper.
 */
public class SerializerImpl implements Serializer {

	/** The conf. */
	private FSTConfiguration conf;

	/** The gson. */
	private Gson gson;

	/**
	 * Instantiates a new serializer.
	 */
	public SerializerImpl() {
		conf = FSTConfiguration.createDefaultConfiguration();
		gson = new Gson();
	}

	/**
	 * As object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param bytes
	 *            the bytes
	 * @param target
	 *            the target
	 * @return the t
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <T> T fromByteArray(byte[] bytes, Class<T> target) {
		return (T) conf.asObject(bytes);
	}

	/**
	 * As byte array.
	 *
	 * @param object
	 *            the object
	 * @return the byte[]
	 */
	@Override
	public byte[] toByteArray(Object object) {
		return conf.asByteArray(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.audit4j.microservice.transport.Serializer#fromJson(java.lang.String,
	 * java.lang.Class)
	 */
	@Override
	public <T> T fromJson(String json, Class<T> target) {
		return gson.fromJson(json, target);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.audit4j.microservice.transport.Serializer#toJson(java.lang.Object)
	 */
	@Override
	public String toJson(Object object) {
		return gson.toJson(object);
	}

}
