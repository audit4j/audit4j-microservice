package org.audit4j.microservice.transport;

// TODO: Auto-generated Javadoc
/**
 * The Interface Serializer.
 */
public interface Serializer {

	/**
	 * From byte array.
	 *
	 * @param <T> the generic type
	 * @param bytes the bytes
	 * @param target the target
	 * @return the t
	 */
	<T> T fromByteArray(byte[] bytes, Class<T> target);
	
	/**
	 * To byte array.
	 *
	 * @param object the object
	 * @return the byte[]
	 */
	byte[] toByteArray(Object object);
	
	/**
	 * From json.
	 *
	 * @param <T> the generic type
	 * @param json the json
	 * @param target the target
	 * @return the t
	 */
	<T> T fromJson(String json, Class<T> target);

	/**
	 * To json.
	 *
	 * @param object the object
	 * @return the string
	 */
	String toJson(Object object);

}
