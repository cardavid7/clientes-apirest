package com.cardavid.clientes.apirest.controller.response;

public class ResponseCodes {

	private String code;
	
	private String message;

	
	public ResponseCodes(String code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResponseCodes [code=" + code + ", message=" + message + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
