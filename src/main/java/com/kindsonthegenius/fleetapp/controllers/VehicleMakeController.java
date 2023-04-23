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

import com.kindsonthegenius.fleetapp.models.VehicleMake;
import com.kindsonthegenius.fleetapp.services.VehicleMakeService;

@Controller
public class VehicleMakeController {

@Autowired private VehicleMakeService vehicleMakeService;
	
	@GetMapping("/vehiclemakes")
	public String getStates(Model model) {
		List<VehicleMake> locationList = vehicleMakeService.getVehicleMakes();
		model.addAttribute("vehiclemakes", locationList);
		
		return "vehiclemake";
	}
	
	@PostMapping("/vehiclemakes/addNew")
	public String addNew(VehicleMake vehiclemake) {
		vehicleMakeService.save(vehiclemake);
		return "redirect:/vehiclemakes";
	}
	
	
	
	@GetMapping("/vehiclemakes/findById/{id}")
    @ResponseBody
	public Optional<VehicleMake> findById(@PathVariable("id") int id) {
		Optional<VehicleMake> vehiclemake = vehicleMakeService.findById(id);
	    if (vehiclemake.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("VehicleMake with id %d not found", id));
	    }

	    return vehiclemake;
	}
	
	@RequestMapping(value="/vehiclemakes/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(VehicleMake vehiclemake) {
		vehicleMakeService.save(vehiclemake);
		return "redirect:/vehiclemakes";
	}
	
	@RequestMapping(value="/vehiclemakes/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		vehicleMakeService.delete(id);
		return "redirect:/vehiclemakes";
	}
}
