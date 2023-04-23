package com.kindsonthegenius.fleetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.VehicleMaintenance;
import com.kindsonthegenius.fleetapp.repositories.VehicleMaintenanceRepository;

@Service
public class VehicleMaintenanceService {

	@Autowired
	private VehicleMaintenanceRepository vehicleMaintenanceRepository;
	
	//Returns the list of vehicleMaintenances from the database
	public List<VehicleMaintenance> getVehicleMaintenances(){
		return vehicleMaintenanceRepository.findAll();
	}
	
	//Save new vehicleMaintenance to the database
	public void save(VehicleMaintenance vehicleMaintenance) {
		vehicleMaintenanceRepository.save(vehicleMaintenance);
	}
	
	//get by id
	public Optional<VehicleMaintenance> findById(int id) {
		Optional<VehicleMaintenance> vehicleMaintenance = vehicleMaintenanceRepository.findById(id);
	    if (vehicleMaintenance.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("VehicleMaintenance with id %d not found", id));
	    }
	    
	    return vehicleMaintenance;
	}

	//Delete a vehicleMaintenance by id
	public void delete(int id) {
		vehicleMaintenanceRepository.deleteById(id);
	}
}
