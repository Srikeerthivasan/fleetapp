package com.kindsonthegenius.fleetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.EmployeeType;
import com.kindsonthegenius.fleetapp.repositories.EmployeeTypeRepository;

@Service
public class EmployeeTypeService {

	@Autowired
	private EmployeeTypeRepository employeeTypeRepository;
	
	//Returns the list of employeeTypes from the database
	public List<EmployeeType> getEmployeeTypes(){
		return employeeTypeRepository.findAll();
	}
	
	//Save new employeeType to the database
	public void save(EmployeeType employeeType) {
		employeeTypeRepository.save(employeeType);
	}
	
	//get by id
	public Optional<EmployeeType> findById(int id) {
		Optional<EmployeeType> employeeType = employeeTypeRepository.findById(id);
	    if (employeeType.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("EmployeeType with id %d not found", id));
	    }
	    
	    return employeeType;
	}

	//Delete a employeeType by id
	public void delete(int id) {
		employeeTypeRepository.deleteById(id);
	}
}
