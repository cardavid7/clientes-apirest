package com.cardavid.clientes.apirest.domain.service;

import java.util.List;

import com.cardavid.clientes.apirest.domain.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public Cliente findById(Long id);
	
	public Cliente save(Cliente cliente);
	
	public void delete(Long id);
	
	public Cliente findByEmail(String email);
}
