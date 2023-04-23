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

import com.kindsonthegenius.fleetapp.models.VehicleModel;
import com.kindsonthegenius.fleetapp.services.VehicleModelService;

@Controller
public class VehicleModelController {

@Autowired private VehicleModelService vehicleModelService;
	
	@GetMapping("/vehiclemodels")
	public String getStates(Model model) {
		List<VehicleModel> locationList = vehicleModelService.getVehicleModels();
		model.addAttribute("vehiclemodels", locationList);

		return "vehiclemodel";
	}
	
	@PostMapping("/vehiclemodels/addNew")
	public String addNew(VehicleModel vehiclemodel) {
		vehicleModelService.save(vehiclemodel);
		return "redirect:/vehiclemodels";
	}
	
	
	
	@GetMapping("/vehiclemodels/findById/{id}")
    @ResponseBody
	public Optional<VehicleModel> findById(@PathVariable("id") int id) {
		Optional<VehicleModel> vehiclemodel = vehicleModelService.findById(id);
	    if (vehiclemodel.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("VehicleModel with id %d not found", id));
	    }

	    return vehiclemodel;
	}
	
	@RequestMapping(value="/vehiclemodels/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(VehicleModel vehiclemodel) {
		vehicleModelService.save(vehiclemodel);
		return "redirect:/vehiclemodels";
	}
	
	@RequestMapping(value="/vehiclemodels/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		vehicleModelService.delete(id);
		return "redirect:/vehiclemodels";
	}
}
