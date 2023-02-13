package com.cardavid.clientes.apirest.controller.response;

public class DataResponse<T> {

	private T data;
	
	private String code;
	
	private String message;


	public DataResponse() {
		super();
	}
	
	public DataResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public DataResponse(T data, String code, String message) {
		super();
		this.data = data;
		this.code = code;
		this.message = message;
	}

	@Override
	public String toString() {
		return "DataResponse [data=" + data + ", code=" + code + ", message=" + message + "]";
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
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
