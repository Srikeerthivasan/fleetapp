package com.kindsonthegenius.fleetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.Client;
import com.kindsonthegenius.fleetapp.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	//Returns the list of clients from the database
	public List<Client> getClients(){
		return clientRepository.findAll();
	}
	
	//Save new client to the database
	public void save(Client client) {
		clientRepository.save(client);
	}
	
	//get by id
	public Optional<Client> findById(int id) {
		Optional<Client> client = clientRepository.findById(id);
	    if (client.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Client with id %d not found", id));
	    }
	    
	    return client;
	}

	//Delete a client by id
	public void delete(int id) {
		clientRepository.deleteById(id);
	}
}
