package org.audit4j.microservice.transport;

import java.io.Serializable;

public class Ack implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8707468501787579530L;

	private String message;
	
	private int code;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public static Ack SUCCESS(){
		Ack ack = new Ack();
		ack.setCode(200);
		ack.setMessage("success");
		return ack;
	}
	
	public static Ack FAIL(){
		Ack ack = new Ack();
		ack.setCode(404);
		ack.setMessage("fail");
		return ack;
	}
}
