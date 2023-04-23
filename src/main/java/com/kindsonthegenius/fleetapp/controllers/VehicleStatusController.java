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

import com.kindsonthegenius.fleetapp.models.VehicleStatus;
import com.kindsonthegenius.fleetapp.services.VehicleStatusService;

@Controller
public class VehicleStatusController {

@Autowired private VehicleStatusService vehicleStatusService;
	
	@GetMapping("/vehiclestatuses")
	public String getStates(Model model) {
		List<VehicleStatus> locationList = vehicleStatusService.getVehicleStatuss();
		model.addAttribute("vehiclestatuses", locationList);
		
		return "vehiclestatus";
	}
	
	@PostMapping("/vehiclestatuses/addNew")
	public String addNew(VehicleStatus vehiclestatus) {
		vehicleStatusService.save(vehiclestatus);
		return "redirect:/vehiclestatuses";
	}
	
	
	
	@GetMapping("/vehiclestatuses/findById/{id}")
    @ResponseBody
	public Optional<VehicleStatus> findById(@PathVariable("id") int id) {
		Optional<VehicleStatus> vehiclestatus = vehicleStatusService.findById(id);
	    if (vehiclestatus.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("VehicleStatus with id %d not found", id));
	    }

	    return vehiclestatus;
	}
	
	@RequestMapping(value="/vehiclestatuses/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(VehicleStatus vehiclestatus) {
		vehicleStatusService.save(vehiclestatus);
		return "redirect:/vehiclestatuses";
	}
	
	@RequestMapping(value="/vehiclestatuses/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		vehicleStatusService.delete(id);
		return "redirect:/vehiclestatuses";
	}
}
