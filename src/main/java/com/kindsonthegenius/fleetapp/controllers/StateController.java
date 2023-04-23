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
import com.kindsonthegenius.fleetapp.models.State;
import com.kindsonthegenius.fleetapp.services.CountryService;
import com.kindsonthegenius.fleetapp.services.StateService;

@Controller
public class StateController {

	
	@Autowired private StateService stateService;
	@Autowired private CountryService countryService;
	
	
	@GetMapping("/states")
	public String getStates(Model model) {
		List<State> stateList = stateService.getStates();
		model.addAttribute("states", stateList);
		
		List<Country> countryList = countryService.getCountries();
		model.addAttribute("countries", countryList);
		
		return "state";
	}
	
	@PostMapping("/states/addNew")
	public String addNew(State state) {
		stateService.save(state);
		return "redirect:/states";
	}
	
	
	
	@GetMapping("/states/findById/{id}")
    @ResponseBody
	public Optional<State> findById(@PathVariable("id") int id) {
		Optional<State> state = stateService.findById(id);
	    if (state.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("State with id %d not found", id));
	    }

	    return state;
	}
	
	@RequestMapping(value="/states/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(State state) {
		stateService.save(state);
		return "redirect:/states";
	}
	
	@RequestMapping(value="/states/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		stateService.delete(id);
		return "redirect:/states";
	}
}
