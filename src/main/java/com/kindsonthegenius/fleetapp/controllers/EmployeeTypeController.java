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

import com.kindsonthegenius.fleetapp.models.EmployeeType;
import com.kindsonthegenius.fleetapp.services.EmployeeTypeService;

@Controller
public class EmployeeTypeController {

@Autowired private EmployeeTypeService employeeTypeService;
	
	@GetMapping("/employeetypes")
	public String getStates(Model model) {
		List<EmployeeType> locationList = employeeTypeService.getEmployeeTypes();
		model.addAttribute("employeetypes", locationList);
		
		return "employeetype";
	}
	
	@PostMapping("/employeetypes/addNew")
	public String addNew(EmployeeType employeetype) {
		employeeTypeService.save(employeetype);
		return "redirect:/employeetypes";
	}
	
	
	
	@GetMapping("/employeetypes/findById/{id}")
    @ResponseBody
	public Optional<EmployeeType> findById(@PathVariable("id") int id) {
		Optional<EmployeeType> employeetype = employeeTypeService.findById(id);
	    if (employeetype.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("EmployeeType with id %d not found", id));
	    }

	    return employeetype;
	}
	
	@RequestMapping(value="/employeetypes/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(EmployeeType employeetype) {
		employeeTypeService.save(employeetype);
		return "redirect:/employeetypes";
	}
	
	@RequestMapping(value="/employeetypes/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		employeeTypeService.delete(id);
		return "redirect:/employeetypes";
	}
}
