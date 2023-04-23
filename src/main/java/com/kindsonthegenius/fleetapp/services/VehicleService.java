package com.kindsonthegenius.fleetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.Vehicle;
import com.kindsonthegenius.fleetapp.repositories.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;
	
	//Returns the list of vehicles from the database
	public List<Vehicle> getVehicles(){
		return vehicleRepository.findAll();
	}
	
	//Save new vehicle to the database
	public void save(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}
	
	//get by id
	public Optional<Vehicle> findById(int id) {
		Optional<Vehicle> vehicle = vehicleRepository.findById(id);
	    if (vehicle.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Vehicle with id %d not found", id));
	    }
	    
	    return vehicle;
	}

	//Delete a vehicle by id
	public void delete(int id) {
		vehicleRepository.deleteById(id);
	}
}
