package com.kindsonthegenius.fleetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.JobTitle;
import com.kindsonthegenius.fleetapp.repositories.JobTitleRepository;

@Service
public class JobTitleService {

	@Autowired
	private JobTitleRepository jobTitleRepository;
	
	//Returns the list of jobTitles from the database
	public List<JobTitle> getJobTitles(){
		return jobTitleRepository.findAll();
	}
	
	//Save new jobTitle to the database
	public void save(JobTitle jobTitle) {
		jobTitleRepository.save(jobTitle);
	}
	
	//get by id
	public Optional<JobTitle> findById(int id) {
		Optional<JobTitle> jobTitle = jobTitleRepository.findById(id);
	    if (jobTitle.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("JobTitle with id %d not found", id));
	    }
	    
	    return jobTitle;
	}

	//Delete a jobTitle by id
	public void delete(int id) {
		jobTitleRepository.deleteById(id);
	}
}
