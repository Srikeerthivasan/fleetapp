package com.kindsonthegenius.fleetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.Contact;
import com.kindsonthegenius.fleetapp.repositories.ContactRepository;

@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	//Returns the list of contacts from the database
	public List<Contact> getContacts(){
		return contactRepository.findAll();
	}
	
	//Save new contact to the database
	public void save(Contact contact) {
		contactRepository.save(contact);
	}
	
	//get by id
	public Optional<Contact> findById(int id) {
		Optional<Contact> contact = contactRepository.findById(id);
	    if (contact.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Contact with id %d not found", id));
	    }
	    
	    return contact;
	}

	//Delete a contact by id
	public void delete(int id) {
		contactRepository.deleteById(id);
	}
}
