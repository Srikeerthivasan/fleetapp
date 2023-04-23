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

import com.kindsonthegenius.fleetapp.models.JobTitle;
import com.kindsonthegenius.fleetapp.services.JobTitleService;

@Controller
public class JobTitleController {

@Autowired private JobTitleService jobTitleService;
	
	@GetMapping("/jobtitles")
	public String getStates(Model model) {
		List<JobTitle> locationList = jobTitleService.getJobTitles();
		model.addAttribute("jobtitles", locationList);
		
		return "jobtitle";
	}
	
	@PostMapping("/jobtitles/addNew")
	public String addNew(JobTitle jobtitle) {
		jobTitleService.save(jobtitle);
		return "redirect:/jobtitles";
	}
	
	
	
	@GetMapping("/jobtitles/findById/{id}")
    @ResponseBody
	public Optional<JobTitle> findById(@PathVariable("id") int id) {
		Optional<JobTitle> jobtitle = jobTitleService.findById(id);
	    if (jobtitle.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("JobTitle with id %d not found", id));
	    }

	    return jobtitle;
	}
	
	@RequestMapping(value="/jobtitles/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(JobTitle jobtitle) {
		jobTitleService.save(jobtitle);
		return "redirect:/jobtitles";
	}
	
	@RequestMapping(value="/jobtitles/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		jobTitleService.delete(id);
		return "redirect:/jobtitles";
	}
}
