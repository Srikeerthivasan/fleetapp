package com.kindsonthegenius.fleetapp.controllers;

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

import com.kindsonthegenius.fleetapp.models.VehicleHire;
import com.kindsonthegenius.fleetapp.services.ClientService;
import com.kindsonthegenius.fleetapp.services.LocationService;
import com.kindsonthegenius.fleetapp.services.VehicleHireService;
import com.kindsonthegenius.fleetapp.services.VehicleService;

@Controller
public class VehicleHireController {

	@Autowired private VehicleHireService vehicleHire;
	@Autowired private VehicleService vehicleService;
	@Autowired private LocationService locationService;
	@Autowired private ClientService clientService;
	
	@GetMapping("/vehiclehires")
	public String getVehicleHires(Model model) {
		model.addAttribute("vehiclehires", vehicleHire.getVehicleHires());
		
		model.addAttribute("vehicles", vehicleService.getVehicles());
		
		model.addAttribute("locations", locationService.getLocations());
		
		model.addAttribute("clients", clientService.getClients());
		
		return "vehiclehire";
	}
	
	@PostMapping("/vehiclehires/addNew")
	public String addNew(VehicleHire vehiclehire) {
		vehicleHire.save(vehiclehire);
		return "redirect:/vehiclehires";
	}
	
	
	
	@GetMapping("/vehiclehires/findById/{id}")
    @ResponseBody
	public Optional<VehicleHire> findById(@PathVariable("id") int id) {
		Optional<VehicleHire> vehiclehire = vehicleHire.findById(id);
	    if (vehiclehire.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("VehicleHire with id %d not found", id));
	    }

	    return vehiclehire;
	}
	
	@RequestMapping(value="/vehiclehires/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(VehicleHire vehiclehire) {
		vehicleHire.save(vehiclehire);
		return "redirect:/vehiclehires";
	}
	
	@RequestMapping(value="/vehiclehires/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		vehicleHire.delete(id);
		return "redirect:/vehiclehires";
	}
}
