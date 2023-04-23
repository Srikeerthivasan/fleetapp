package com.kindsonthegenius.fleetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.Country;
import com.kindsonthegenius.fleetapp.repositories.CountryRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;
	
	//Returns the list of countries from the database
	public List<Country> getCountries(){
		return countryRepository.findAll();
	}
	
	//Save new country to the database
	public void save(Country country) {
		countryRepository.save(country);
	}
	
	//get by id
	public Optional<Country> findById(int id) {
		Optional<Country> country = countryRepository.findById(id);
	    if (country.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Country with id %d not found", id));
	    }
	    
	    return country;
	}

	//Delete a country by id
	public void delete(int id) {
		countryRepository.deleteById(id);
	}
}
