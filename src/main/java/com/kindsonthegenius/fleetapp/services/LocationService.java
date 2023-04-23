package com.kindsonthegenius.fleetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.Location;
import com.kindsonthegenius.fleetapp.repositories.LocationRepository;

@Service
public class LocationService {
	@Autowired
	private LocationRepository locationRepository;
	
	//Returns the list of locations from the database
	public List<Location> getLocations(){
		return locationRepository.findAll();
	}
	
	//Save new location to the database
	public void save(Location location) {
		locationRepository.save(location);
	}
	
	//get by id
	public Optional<Location> findById(int id) {
		Optional<Location> location = locationRepository.findById(id);
	    if (location.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Location with id %d not found", id));
	    }
	    
	    return location;
	}

	//Delete a location by id
	public void delete(int id) {
		locationRepository.deleteById(id);
	}
}
