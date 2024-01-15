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

import com.kindsonthegenius.fleetapp.models.Employee;
import com.kindsonthegenius.fleetapp.models.EmployeeType;
import com.kindsonthegenius.fleetapp.models.JobTitle;
import com.kindsonthegenius.fleetapp.models.Country;
import com.kindsonthegenius.fleetapp.models.State;
import com.kindsonthegenius.fleetapp.services.EmployeeService;
import com.kindsonthegenius.fleetapp.services.EmployeeTypeService;
import com.kindsonthegenius.fleetapp.services.JobTitleService;
import com.kindsonthegenius.fleetapp.services.CountryService;
import com.kindsonthegenius.fleetapp.services.StateService;

@Controller
public class EmployeeController {

	@Autowired private EmployeeService employeeService;
	@Autowired private CountryService countryService;
	@Autowired private StateService stateService;
	@Autowired private JobTitleService jobTitleService;
	@Autowired private EmployeeTypeService employeeTypeService;
	
	@GetMapping("/employees")
	public String getEmployees(Model model) {
		List<Employee> employeeList = employeeService.getEmployees();
		model.addAttribute("employees", employeeList);
		
		List<Country> countryList = countryService.getCountries();
		model.addAttribute("countries", countryList);
		
		List<State> stateList = stateService.getStates();
		model.addAttribute("states", stateList);
		
		List<JobTitle> jobTitleList = jobTitleService.getJobTitles();
		model.addAttribute("jobtitles", jobTitleList);
		
		List<EmployeeType> employeeTypeList = employeeTypeService.getEmployeeTypes();
		model.addAttribute("employeetypes", employeeTypeList);
		
		return "employee";
	}
	
	@PostMapping("/employees/addNew")
	public String addNew(Employee employee) {
		employeeService.save(employee);
		return "redirect:/employees";
	}
	
	
	
	@GetMapping("/employees/findById/{id}")
    @ResponseBody
	public Optional<Employee> findById(@PathVariable("id") int id) {
		Optional<Employee> employee = employeeService.findById(id);
	    if (employee.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Employee with id %d not found", id));
	    }

	    return employee;
	}
	
	@RequestMapping(value="/employees/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(Employee employee) {
		employeeService.save(employee);
		return "redirect:/employees";
	}
	
	@RequestMapping(value="/employees/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		employeeService.delete(id);
		return "redirect:/employees";
	}
	
	//Assign Employee Username
	@RequestMapping(value = "/employees/assignUsername/{id}")
	public  String assignUsername(@PathVariable("id") int id){
		employeeService.assignUsername(id);
		return "redirect:/employees";
	}
}
