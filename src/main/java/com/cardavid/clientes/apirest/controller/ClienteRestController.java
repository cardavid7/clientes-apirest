package com.cardavid.clientes.apirest.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cardavid.clientes.apirest.controller.response.DataResponse;
import com.cardavid.clientes.apirest.controller.response.ResponseValues;
import com.cardavid.clientes.apirest.domain.entity.Cliente;
import com.cardavid.clientes.apirest.domain.service.IClienteService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {
	
	protected final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private IClienteService clienteService;
	

	//**************************************************** Normal Response ****************************************************//
	
	@GetMapping("/getAllClientes")
	public List<Cliente> getAllClientes (){
		try {
			return clienteService.findAll();
		}catch (Exception e) {
			logger.error("==> Error getAllClientes: "+e.toString(),e);
			return null;
		}
	}
	
	@GetMapping("/getClienteById/{id}")
	public Cliente getClienteById(@PathVariable Long id) {
		try {
			return clienteService.findById(id);
		}catch (Exception e) {
			logger.error("==> Error getClienteById: "+e.toString(),e);
			return null;
		}
	}
	
	@PostMapping("/createCliente")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente createCliente(@RequestBody Cliente cliente) {
		try {

			Cliente clienteDB = null;
			if(cliente.getEmail() != null && !cliente.getEmail().isEmpty()) {
				clienteDB = clienteService.findByEmail(cliente.getEmail());
				
				if (clienteDB != null) return clienteDB; 
			}
			return clienteService.save(cliente);
			
		}catch (Exception e) {
			logger.error("==> Error createCliente: "+e.toString(),e);
			return null;
		}
	}
	
	@PutMapping("/editClienteById/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente editClienteById(@RequestBody Cliente cliente, @PathVariable Long id) {
		try {
			Cliente clienteDB = null;
			
			clienteDB = clienteService.findById(id);
			clienteDB.setApellidos(cliente.getApellidos());
			clienteDB.setNombres(cliente.getNombres());
			clienteDB.setEmail(cliente.getEmail());
			
			return clienteService.save(clienteDB);
		}catch (Exception e) {
			logger.error("==> Error editClienteById: "+e.toString(),e);
			return null;
		}
	}

	@DeleteMapping("/deleteCliente/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCliente(@PathVariable Long id) {
		try {
			clienteService.delete(id);
		}catch (Exception e) {
			logger.error("==> Error deleteCliente: "+e.toString(),e);
		}
	}
	
	
	//**************************************************** ResponseEntity - DataResponse ****************************************************//
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@GetMapping("/dataResponse/getAllClientes")
	public DataResponse<List<Cliente>> getAllClientesDataResponse (){
		logger.info("Executed getAllClientesDataResponse");
		try{
			return new DataResponse(clienteService.findAll(),ResponseValues.OK.getCode(),ResponseValues.OK.getMessage());
		}catch(DataAccessException e) {
			logger.error("==> Error getAllClientesDataResponse: "+e,e);
			return new DataResponse(null,ResponseValues.ERROR.getCode(),e.getMessage()+" : "+e.getMostSpecificCause().getMessage());
		}catch(Exception e) {
			logger.error("==> Error getAllClientesDataResponse: "+e,e);
			return new DataResponse(null,ResponseValues.ERROR.getCode(),e.getMessage());
		}
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@GetMapping("/responseEntity/getAllClientes")
	public ResponseEntity<?> getAllClientesResponseEntity (){
		logger.info("Executed getAllClientesResponseEntity");
		DataResponse dataResponse = null;
		try{
			dataResponse = new DataResponse<>(clienteService.findAll(), ResponseValues.OK.getCode(), ResponseValues.OK.getMessage());

			return new ResponseEntity(dataResponse, HttpStatus.OK);
		}catch(DataAccessException e) {
			logger.error("==> Error getAllClientesResponseEntity: "+e,e);
			dataResponse = new DataResponse<>(null, ResponseValues.ERROR.getCode(), e.getMessage()+" : "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity(dataResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(Exception e) {
			logger.error("==> Error getAllClientesResponseEntity: "+e,e);
			dataResponse = new DataResponse<>(null, ResponseValues.ERROR.getCode(), e.getMessage());
			return new ResponseEntity(dataResponse, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@GetMapping("/responseEntity/getClienteById/{id}")
	public ResponseEntity<?> getClienteByIdResponseEntity (@PathVariable Long id) {
		logger.info("Executed getClienteByIdResponseEntity");
		try {
			Cliente clienteDB = clienteService.findById(id);
			if(clienteDB == null) {
				return new ResponseEntity(new DataResponse<>(clienteDB, ResponseValues.DATA_NOT_FOUND.getCode(), ResponseValues.DATA_NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity(new DataResponse<>(clienteDB, ResponseValues.OK.getCode(), ResponseValues.OK.getMessage()), HttpStatus.OK);
		}catch(DataAccessException e) {
			logger.error("==> Error getClienteByIdResponseEntity: "+e,e);
			return new ResponseEntity(new DataResponse<>(null, ResponseValues.ERROR.getCode(), e.getMessage()+" : "+e.getMostSpecificCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(Exception e) {
			logger.error("==> Error getClienteByIdResponseEntity: "+e,e);
			return new ResponseEntity(new DataResponse<>(null, ResponseValues.ERROR.getCode(), e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@PostMapping("/responseEntity/createCliente")
	public ResponseEntity<?> createClienteResponseEntity (@RequestBody Cliente cliente) {
		logger.info("Executed createClienteResponseEntity");
		try {
			
			if (cliente.getNombres() == null || cliente.getNombres().isEmpty()) {
				return new ResponseEntity(new DataResponse<>(null, ResponseValues.ERROR.getCode(), "Campo nombres es requerido para crear el cliente"), HttpStatus.BAD_REQUEST);
			}
			
			if (cliente.getApellidos() == null || cliente.getApellidos().isEmpty()) {
				return new ResponseEntity(new DataResponse<>(null, ResponseValues.ERROR.getCode(), "Campo apellidos es requerido para crear el cliente"), HttpStatus.BAD_REQUEST);
			}
			
			if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
				return new ResponseEntity(new DataResponse<>(null, ResponseValues.ERROR.getCode(), "Campo email es requerido para crear el cliente"), HttpStatus.BAD_REQUEST);
			}else {
				Cliente clienteDB = clienteService.findByEmail(cliente.getEmail());
				if(clienteDB != null) {
					return new ResponseEntity(new DataResponse<>(null, ResponseValues.ERROR.getCode(), "Email ya fue registrado, por favor ingrese otro email"), HttpStatus.BAD_REQUEST);
				}
			}
			
			return new ResponseEntity(new DataResponse<>(clienteService.save(cliente), ResponseValues.OK.getCode(), ResponseValues.OK.getMessage()), HttpStatus.CREATED);
		}catch(DataAccessException e) {
			logger.error("==> Error createClienteResponseEntity: "+e,e);
			return new ResponseEntity(new DataResponse<>(null, ResponseValues.ERROR.getCode(), e.getMessage()+" : "+e.getMostSpecificCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(Exception e) {
			logger.error("==> Error createClienteResponseEntity: "+e,e);
			return new ResponseEntity(new DataResponse<>(null, ResponseValues.ERROR.getCode(), e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@PutMapping("/responseEntity/editClienteById/{id}")
	public ResponseEntity<?> editClienteByIdResponseEntity (@RequestBody Cliente cliente, @PathVariable Long id) {
		logger.info("Executed editClienteByIdResponseEntity");
		try {
			Cliente clienteDB = null;
			
			clienteDB = clienteService.findById(id);
			
			if(clienteDB == null) {
				return new ResponseEntity(new DataResponse<>(clienteDB, ResponseValues.DATA_NOT_FOUND.getCode(), ResponseValues.DATA_NOT_FOUND.getMessage()), HttpStatus.NOT_FOUND);
			}
			
			if (cliente.getNombres() == null || cliente.getNombres().isEmpty()) {
				return new ResponseEntity(new DataResponse<>(null, ResponseValues.ERROR.getCode(), "Campo nombres es requerido para editar el cliente"), HttpStatus.BAD_REQUEST);
			}
			
			if (cliente.getApellidos() == null || cliente.getApellidos().isEmpty()) {
				return new ResponseEntity(new DataResponse<>(null, ResponseValues.ERROR.getCode(), "Campo apellidos es requerido para editar el cliente"), HttpStatus.BAD_REQUEST);
			}
			
			if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
				return new ResponseEntity(new DataResponse<>(null, ResponseValues.ERROR.getCode(), "Campo email es requerido para editar el cliente"), HttpStatus.BAD_REQUEST);
			}else {
				Cliente clienteDB2 = clienteService.findByEmail(cliente.getEmail());
				if(clienteDB2 != null && !clienteDB.getId().equals(clienteDB2.getId())) {
					return new ResponseEntity(new DataResponse<>(null, ResponseValues.ERROR.getCode(), "Email ya fue registrado, por favor ingrese otro email"), HttpStatus.BAD_REQUEST);
				}
			}
			
			clienteDB.setApellidos(cliente.getApellidos());
			clienteDB.setNombres(cliente.getNombres());
			clienteDB.setEmail(cliente.getEmail());
			clienteDB = clienteService.save(clienteDB);
			
			return new ResponseEntity(new DataResponse<>(clienteDB, ResponseValues.OK.getCode(), ResponseValues.OK.getMessage()), HttpStatus.OK);
		}catch(DataAccessException e) {
			logger.error("==> Error editClienteByIdResponseEntity: "+e,e);
			return new ResponseEntity(new DataResponse<>(null, ResponseValues.ERROR.getCode(), e.getMessage()+" : "+e.getMostSpecificCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(Exception e) {
			logger.error("==> Error editClienteByIdResponseEntity: "+e,e);
			return new ResponseEntity(new DataResponse<>(null, ResponseValues.ERROR.getCode(), e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	@DeleteMapping("/responseEntity/deleteCliente/{id}")
	public ResponseEntity<?> deleteClienteResponseEntity (@PathVariable Long id) {
		logger.info("Executed deleteClienteResponseEntity");
		try {
			clienteService.delete(id);
			return new ResponseEntity(new DataResponse<>("Cliente con id "+id+" eliminado", ResponseValues.OK.getCode(), ResponseValues.OK.getMessage()), HttpStatus.OK);
		}catch(DataAccessException e) {
			logger.error("==> Error deleteClienteResponseEntity: "+e,e);
			return new ResponseEntity(new DataResponse<>(null, ResponseValues.ERROR.getCode(), e.getMessage()+" : "+e.getMostSpecificCause().getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}catch(Exception e) {
			logger.error("==> Error deleteClienteResponseEntity: "+e,e);
			return new ResponseEntity(new DataResponse<>(null, ResponseValues.ERROR.getCode(), e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}
}
