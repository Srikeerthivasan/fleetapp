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

import com.kindsonthegenius.fleetapp.models.Contact;
import com.kindsonthegenius.fleetapp.models.Country;
import com.kindsonthegenius.fleetapp.models.State;
import com.kindsonthegenius.fleetapp.services.ContactService;
import com.kindsonthegenius.fleetapp.services.CountryService;
import com.kindsonthegenius.fleetapp.services.StateService;

@Controller
public class ContactController {

	@Autowired private ContactService contactService;
	@Autowired private CountryService countryService;
	@Autowired private StateService stateService;
	
	@GetMapping("/contacts")
	public String getContacts(Model model) {
		List<Contact> contactList = contactService.getContacts();
		model.addAttribute("contacts", contactList);
		
		List<Country> countryList = countryService.getCountries();
		model.addAttribute("countries", countryList);
		
		List<State> stateList = stateService.getStates();
		model.addAttribute("states", stateList);
		
		return "contact";
	}
	
	@PostMapping("/contacts/addNew")
	public String addNew(Contact contact) {
		contactService.save(contact);
		return "redirect:/contacts";
	}
	
	
	
	@GetMapping("/contacts/findById/{id}")
    @ResponseBody
	public Optional<Contact> findById(@PathVariable("id") int id) {
		Optional<Contact> contact = contactService.findById(id);
	    if (contact.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Contact with id %d not found", id));
	    }

	    return contact;
	}
	
	@RequestMapping(value="/contacts/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(Contact contact) {
		contactService.save(contact);
		return "redirect:/contacts";
	}
	
	@RequestMapping(value="/contacts/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		contactService.delete(id);
		return "redirect:/contacts";
	}
}
