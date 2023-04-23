package com.kindsonthegenius.fleetapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.Country;
import com.kindsonthegenius.fleetapp.models.Location;
import com.kindsonthegenius.fleetapp.models.State;
import com.kindsonthegenius.fleetapp.services.CountryService;
import com.kindsonthegenius.fleetapp.services.LocationService;
import com.kindsonthegenius.fleetapp.services.StateService;

@Controller
public class LocationController {

	@Autowired private StateService stateService;
	@Autowired private CountryService countryService;
	@Autowired private LocationService locationService;
	
	@GetMapping("/locations")
	public String getStates(Model model) {
		List<Location> locationList = locationService.getLocations();
		model.addAttribute("locations", locationList);
		
		List<Country> countryList = countryService.getCountries();
		model.addAttribute("countries", countryList);
		
		List<State> stateList = stateService.getStates();
		model.addAttribute("states", stateList);
		
		return "location";
	}
	
	@PostMapping("/locations/addNew")
	public String addNew(Location location) {
		locationService.save(location);
		return "redirect:/locations";
	}
	
	
	
	@GetMapping("/locations/findById/{id}")
    @ResponseBody
	public Optional<Location> findById(@PathVariable("id") int id) {
		Optional<Location> location = locationService.findById(id);
	    if (location.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Location with id %d not found", id));
	    }

	    return location;
	}
	
	@RequestMapping(value="/locations/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(Location location) {
		locationService.save(location);
		return "redirect:/locations";
	}
	
	@RequestMapping(value="/locations/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		locationService.delete(id);
		return "redirect:/locations";
	}
}
