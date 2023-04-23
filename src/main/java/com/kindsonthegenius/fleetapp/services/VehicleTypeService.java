package com.kindsonthegenius.fleetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.VehicleType;
import com.kindsonthegenius.fleetapp.repositories.VehicleTypeRepository;

@Service
public class VehicleTypeService {
	
	@Autowired
	private VehicleTypeRepository vehicleTypeRepository;
	
	//Returns the list of vehicleTypes from the database
	public List<VehicleType> getVehicleTypes(){
		return vehicleTypeRepository.findAll();
	}
	
	//Save new vehicleType to the database
	public void save(VehicleType vehicleType) {
		vehicleTypeRepository.save(vehicleType);
	}
	
	//get by id
	public Optional<VehicleType> findById(int id) {
		Optional<VehicleType> vehicleType = vehicleTypeRepository.findById(id);
	    if (vehicleType.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("VehicleType with id %d not found", id));
	    }
	    
	    return vehicleType;
	}

	//Delete a vehicleType by id
	public void delete(int id) {
		vehicleTypeRepository.deleteById(id);
	}
}
