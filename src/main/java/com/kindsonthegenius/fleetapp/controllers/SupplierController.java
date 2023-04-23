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

import com.kindsonthegenius.fleetapp.models.Supplier;
import com.kindsonthegenius.fleetapp.models.Country;
import com.kindsonthegenius.fleetapp.models.State;
import com.kindsonthegenius.fleetapp.services.SupplierService;
import com.kindsonthegenius.fleetapp.services.CountryService;
import com.kindsonthegenius.fleetapp.services.StateService;

@Controller
public class SupplierController {

	@Autowired private SupplierService supplierService;
	@Autowired private CountryService countryService;
	@Autowired private StateService stateService;
	
	@GetMapping("/suppliers")
	public String getSuppliers(Model model) {
		List<Supplier> supplierList = supplierService.getSuppliers();
		model.addAttribute("suppliers", supplierList);
		
		List<Country> countryList = countryService.getCountries();
		model.addAttribute("countries", countryList);
		
		List<State> stateList = stateService.getStates();
		model.addAttribute("states", stateList);
		
		return "supplier";
	}
	
	@PostMapping("/suppliers/addNew")
	public String addNew(Supplier supplier) {
		supplierService.save(supplier);
		return "redirect:/suppliers";
	}
	
	
	
	@GetMapping("/suppliers/findById/{id}")
    @ResponseBody
	public Optional<Supplier> findById(@PathVariable("id") int id) {
		Optional<Supplier> supplier = supplierService.findById(id);
	    if (supplier.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Supplier with id %d not found", id));
	    }

	    return supplier;
	}
	
	@RequestMapping(value="/suppliers/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(Supplier supplier) {
		supplierService.save(supplier);
		return "redirect:/suppliers";
	}
	
	@RequestMapping(value="/suppliers/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		supplierService.delete(id);
		return "redirect:/suppliers";
	}
}
