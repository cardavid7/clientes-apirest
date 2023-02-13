package com.cardavid.clientes.apirest.controller.response;

public interface ResponseValues {

	public static final ResponseCodes OK = new ResponseCodes("OK", "OK");
	
	public static final ResponseCodes ERROR = new ResponseCodes("ERROR", "Error en la transacción");
	
	public static final ResponseCodes DATA_NOT_FOUND = new ResponseCodes("DATA_NOT_FOUND", "Datos no encontrados");
}
