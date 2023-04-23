package com.kindsonthegenius.fleetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.VehicleMake;
import com.kindsonthegenius.fleetapp.repositories.VehicleMakeRepository;

@Service
public class VehicleMakeService {
	
	@Autowired
	private VehicleMakeRepository vehicleMakeRepository;
	
	//Returns the list of vehicleMakes from the database
	public List<VehicleMake> getVehicleMakes(){
		return vehicleMakeRepository.findAll();
	}
	
	//Save new vehicleMake to the database
	public void save(VehicleMake vehicleMake) {
		vehicleMakeRepository.save(vehicleMake);
	}
	
	//get by id
	public Optional<VehicleMake> findById(int id) {
		Optional<VehicleMake> vehicleMake = vehicleMakeRepository.findById(id);
	    if (vehicleMake.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("VehicleMake with id %d not found", id));
	    }
	    
	    return vehicleMake;
	}

	//Delete a vehicleMake by id
	public void delete(int id) {
		vehicleMakeRepository.deleteById(id);
	}
}
