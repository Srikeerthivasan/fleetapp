package com.kindsonthegenius.fleetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.VehicleMovement;
import com.kindsonthegenius.fleetapp.repositories.VehicleMovementRepository;

@Service
public class VehicleMovementService {

	@Autowired
	private VehicleMovementRepository vehicleMovementRepository;
	
	//Returns the list of vehicleMovements from the database
	public List<VehicleMovement> getVehicleMovements(){
		return vehicleMovementRepository.findAll();
	}
	
	//Save new vehicleMovement to the database
	public void save(VehicleMovement vehicleMovement) {
		vehicleMovementRepository.save(vehicleMovement);
	}
	
	//get by id
	public Optional<VehicleMovement> findById(int id) {
		Optional<VehicleMovement> vehicleMovement = vehicleMovementRepository.findById(id);
	    if (vehicleMovement.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("VehicleMovement with id %d not found", id));
	    }
	    
	    return vehicleMovement;
	}

	//Delete a vehicleMovement by id
	public void delete(int id) {
		vehicleMovementRepository.deleteById(id);
	}
}
