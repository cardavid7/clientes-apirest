package com.cardavid.clientes.apirest.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardavid.clientes.apirest.domain.dao.IClienteDao;
import com.cardavid.clientes.apirest.domain.entity.Cliente;
import com.cardavid.clientes.apirest.domain.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private IClienteDao clienteDao;
	
	
	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteDao.findAll();
	}


	@Override
	public Cliente findById(Long id) {
		return clienteDao.findById(id).orElse(null);
	}


	@Override
	public Cliente save(Cliente cliente) {
		return clienteDao.save(cliente);
	}


	@Override
	public void delete(Long id) {
		clienteDao.deleteById(id);
		
	}


	@Override
	public Cliente findByEmail(String email) {
		return clienteDao.findByEmail(email);
	}
	
	

}
