package com.kindsonthegenius.fleetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.VehicleModel;
import com.kindsonthegenius.fleetapp.repositories.VehicleModelRepository;

@Service
public class VehicleModelService {

	@Autowired
	private VehicleModelRepository vehicleModelRepository;
	
	//Returns the list of vehicleModels from the database
	public List<VehicleModel> getVehicleModels(){
		return vehicleModelRepository.findAll();
	}
	
	//Save new vehicleModel to the database
	public void save(VehicleModel vehicleModel) {
		vehicleModelRepository.save(vehicleModel);
	}
	
	//get by id
	public Optional<VehicleModel> findById(int id) {
		Optional<VehicleModel> vehicleModel = vehicleModelRepository.findById(id);
	    if (vehicleModel.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("VehicleModel with id %d not found", id));
	    }
	    
	    return vehicleModel;
	}

	//Delete a vehicleModel by id
	public void delete(int id) {
		vehicleModelRepository.deleteById(id);
	}
}
