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
import com.kindsonthegenius.fleetapp.models.Vehicle;
import com.kindsonthegenius.fleetapp.models.VehicleMovement;
import com.kindsonthegenius.fleetapp.services.LocationService;
import com.kindsonthegenius.fleetapp.services.SupplierService;
import com.kindsonthegenius.fleetapp.services.VehicleMovementService;
import com.kindsonthegenius.fleetapp.services.VehicleService;

@Controller
public class VehicleMovementController {

	@Autowired private VehicleMovementService vehicleMovement;
	@Autowired private VehicleService vehicleService;
	@Autowired private LocationService locationService;
	
	@GetMapping("/vehiclemovements")
	public String getVehicleMovements(Model model) {
		model.addAttribute("vehiclemovements", vehicleMovement.getVehicleMovements());
		
		model.addAttribute("vehicles", vehicleService.getVehicles());
		
		model.addAttribute("locations", locationService.getLocations());
		
		return "vehiclemovement";
	}
	
	@PostMapping("/vehiclemovements/addNew")
	public String addNew(VehicleMovement vehiclemovement) {
		vehicleMovement.save(vehiclemovement);
		return "redirect:/vehiclemovements";
	}
	
	
	
	@GetMapping("/vehiclemovements/findById/{id}")
    @ResponseBody
	public Optional<VehicleMovement> findById(@PathVariable("id") int id) {
		Optional<VehicleMovement> vehiclemovement = vehicleMovement.findById(id);
	    if (vehiclemovement.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("VehicleMovement with id %d not found", id));
	    }

	    return vehiclemovement;
	}
	
	@RequestMapping(value="/vehiclemovements/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(VehicleMovement vehiclemovement) {
		vehicleMovement.save(vehiclemovement);
		return "redirect:/vehiclemovements";
	}
	
	@RequestMapping(value="/vehiclemovements/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		vehicleMovement.delete(id);
		return "redirect:/vehiclemovements";
	}
}
