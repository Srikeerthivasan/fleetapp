package com.kindsonthegenius.fleetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.VehicleStatus;
import com.kindsonthegenius.fleetapp.repositories.VehicleStatusRepository;

@Service
public class VehicleStatusService {

	@Autowired
	private VehicleStatusRepository vehicleStatusRepository;
	
	//Returns the list of vehicleStatuses from the database
	public List<VehicleStatus> getVehicleStatuss(){
		return vehicleStatusRepository.findAll();
	}
	
	//Save new vehicleStatus to the database
	public void save(VehicleStatus vehicleStatus) {
		vehicleStatusRepository.save(vehicleStatus);
	}
	
	//get by id
	public Optional<VehicleStatus> findById(int id) {
		Optional<VehicleStatus> vehicleStatus = vehicleStatusRepository.findById(id);
	    if (vehicleStatus.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("VehicleStatus with id %d not found", id));
	    }
	    
	    return vehicleStatus;
	}

	//Delete a vehicleStatus by id
	public void delete(int id) {
		vehicleStatusRepository.deleteById(id);
	}
}
