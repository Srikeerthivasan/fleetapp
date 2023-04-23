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

import com.kindsonthegenius.fleetapp.models.Vehicle;
import com.kindsonthegenius.fleetapp.services.EmployeeService;
import com.kindsonthegenius.fleetapp.services.LocationService;
import com.kindsonthegenius.fleetapp.services.VehicleMakeService;
import com.kindsonthegenius.fleetapp.services.VehicleModelService;
import com.kindsonthegenius.fleetapp.services.VehicleService;
import com.kindsonthegenius.fleetapp.services.VehicleStatusService;
import com.kindsonthegenius.fleetapp.services.VehicleTypeService;

@Controller
public class VehicleController {

	@Autowired private VehicleService vehicleService;
	@Autowired private LocationService locationService;
	@Autowired private EmployeeService employeeService;
	@Autowired private VehicleMakeService vehicleMakeService;
	@Autowired private VehicleStatusService vehicleStatusService;
	@Autowired private VehicleTypeService vehicleTypeService;
	@Autowired private VehicleModelService vehicleModelService;
	
	@GetMapping("/vehicles")
	public String getVehicles(Model model) {
		model.addAttribute("vehicles", vehicleService.getVehicles());
		model.addAttribute("locations", locationService.getLocations());
		model.addAttribute("employees", employeeService.getEmployees());
		model.addAttribute("vehiclemakes", vehicleMakeService.getVehicleMakes());
		model.addAttribute("vehiclestatuses", vehicleStatusService.getVehicleStatuss());
		model.addAttribute("vehicletypes", vehicleTypeService.getVehicleTypes());
		model.addAttribute("vehiclemodels", vehicleModelService.getVehicleModels());
		
		return "vehicle";
	}
	
	@PostMapping("/vehicles/addNew")
	public String addNew(Vehicle vehicle) {
		vehicleService.save(vehicle);
		return "redirect:/vehicles";
	}
	
	
	
	@GetMapping("/vehicles/findById/{id}")
    @ResponseBody
	public Optional<Vehicle> findById(@PathVariable("id") int id) {
		Optional<Vehicle> vehicle = vehicleService.findById(id);
	    if (vehicle.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Vehicle with id %d not found", id));
	    }

	    return vehicle;
	}
	
	@RequestMapping(value="/vehicles/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(Vehicle vehicle) {
		vehicleService.save(vehicle);
		return "redirect:/vehicles";
	}
	
	@RequestMapping(value="/vehicles/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		vehicleService.delete(id);
		return "redirect:/vehicles";
	}
}
