package com.kindsonthegenius.fleetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.Supplier;
import com.kindsonthegenius.fleetapp.repositories.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;
	
	//Returns the list of suppliers from the database
	public List<Supplier> getSuppliers(){
		return supplierRepository.findAll();
	}
	
	//Save new supplier to the database
	public void save(Supplier supplier) {
		supplierRepository.save(supplier);
	}
	
	//get by id
	public Optional<Supplier> findById(int id) {
		Optional<Supplier> supplier = supplierRepository.findById(id);
	    if (supplier.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Supplier with id %d not found", id));
	    }
	    
	    return supplier;
	}

	//Delete a supplier by id
	public void delete(int id) {
		supplierRepository.deleteById(id);
	}
}
