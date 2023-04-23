package com.kindsonthegenius.fleetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.Invoice;
import com.kindsonthegenius.fleetapp.repositories.InvoiceRepository;

@Service
public class InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	//Returns the list of invoices from the database
	public List<Invoice> getInvoices(){
		return invoiceRepository.findAll();
	}
	
	//Save new invoice to the database
	public void save(Invoice invoice) {
		invoiceRepository.save(invoice);
	}
	
	//get by id
	public Optional<Invoice> findById(int id) {
		Optional<Invoice> invoice = invoiceRepository.findById(id);
	    if (invoice.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Invoice with id %d not found", id));
	    }
	    
	    return invoice;
	}

	//Delete a invoice by id
	public void delete(int id) {
		invoiceRepository.deleteById(id);
	}
}
