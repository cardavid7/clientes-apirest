package com.cardavid.clientes.apirest.domain.dao;

import org.springframework.data.repository.CrudRepository;

import com.cardavid.clientes.apirest.domain.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long>{
	
	public Cliente findByEmail(String email);

}
