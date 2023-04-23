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

import com.kindsonthegenius.fleetapp.models.VehicleType;
import com.kindsonthegenius.fleetapp.services.VehicleTypeService;

@Controller
public class VehicleTypeController {

@Autowired private VehicleTypeService vehicleTypeService;
	
	@GetMapping("/vehicletypes")
	public String getStates(Model model) {
		List<VehicleType> locationList = vehicleTypeService.getVehicleTypes();
		model.addAttribute("vehicletypes", locationList);
		
		return "vehicletype";
	}
	
	@PostMapping("/vehicletypes/addNew")
	public String addNew(VehicleType vehicletype) {
		vehicleTypeService.save(vehicletype);
		return "redirect:/vehicletypes";
	}
	
	
	
	@GetMapping("/vehicletypes/findById/{id}")
    @ResponseBody
	public Optional<VehicleType> findById(@PathVariable("id") int id) {
		Optional<VehicleType> vehicletype = vehicleTypeService.findById(id);
	    if (vehicletype.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("VehicleType with id %d not found", id));
	    }

	    return vehicletype;
	}
	
	@RequestMapping(value="/vehicletypes/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(VehicleType vehicletype) {
		vehicleTypeService.save(vehicletype);
		return "redirect:/vehicletypes";
	}
	
	@RequestMapping(value="/vehicletypes/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		vehicleTypeService.delete(id);
		return "redirect:/vehicletypes";
	}
}
