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
import com.kindsonthegenius.fleetapp.models.Client;
import com.kindsonthegenius.fleetapp.services.CountryService;
import com.kindsonthegenius.fleetapp.services.StateService;
import com.kindsonthegenius.fleetapp.services.ClientService;

@Controller
public class ClientController {

	@Autowired private ClientService clientService;
	@Autowired private CountryService countryService;
	@Autowired private StateService stateService;
	
	@GetMapping("/clients")
	public String getClients(Model model) {
		List<Client> clientList = clientService.getClients();
		model.addAttribute("clients", clientList);
		
		List<Country> countryList = countryService.getCountries();
		model.addAttribute("countries", countryList);
		
		List<State> stateList = stateService.getStates();
		model.addAttribute("states", stateList);
		
		return "client";
	}
	
	@PostMapping("/clients/addNew")
	public String addNew(Client client) {
		clientService.save(client);
		return "redirect:/clients";
	}
	
	
	
	@GetMapping("/clients/findById/{id}")
    @ResponseBody
	public Optional<Client> findById(@PathVariable("id") int id) {
		Optional<Client> client = clientService.findById(id);
	    if (client.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Client with id %d not found", id));
	    }

	    return client;
	}
	
	@RequestMapping(value="/clients/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(Client client) {
		clientService.save(client);
		return "redirect:/clients";
	}
	
	@RequestMapping(value="/clients/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		clientService.delete(id);
		return "redirect:/clients";
	}
}
