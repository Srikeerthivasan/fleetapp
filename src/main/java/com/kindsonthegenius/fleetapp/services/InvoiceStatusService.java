package com.kindsonthegenius.fleetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.InvoiceStatus;
import com.kindsonthegenius.fleetapp.repositories.InvoiceStatusRepository;

@Service
public class InvoiceStatusService {
	
	@Autowired
	private InvoiceStatusRepository invoiceStatusRepository;
	
	//Returns the list of invoiceStatuss from the database
	public List<InvoiceStatus> getInvoiceStatuss(){
		return invoiceStatusRepository.findAll();
	}
	
	//Save new invoiceStatus to the database
	public void save(InvoiceStatus invoiceStatus) {
		invoiceStatusRepository.save(invoiceStatus);
	}
	
	//get by id
	public Optional<InvoiceStatus> findById(int id) {
		Optional<InvoiceStatus> invoiceStatus = invoiceStatusRepository.findById(id);
	    if (invoiceStatus.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("InvoiceStatus with id %d not found", id));
	    }
	    
	    return invoiceStatus;
	}

	//Delete a invoiceStatus by id
	public void delete(int id) {
		invoiceStatusRepository.deleteById(id);
	}
}
