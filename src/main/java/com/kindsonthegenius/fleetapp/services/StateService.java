package com.kindsonthegenius.fleetapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.State;
import com.kindsonthegenius.fleetapp.repositories.StateRepository;

@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;
	
	//Returns the list of states from the database
	public List<State> getStates(){
		return stateRepository.findAll();
	}
	
	//Save new state to the database
	public void save(State state) {
		stateRepository.save(state);
	}
	
	//get by id
	public Optional<State> findById(int id) {
		Optional<State> state = stateRepository.findById(id);
	    if (state.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("State with id %d not found", id));
	    }
	    
	    return state;
	}

	//Delete a state by id
	public void delete(int id) {
		stateRepository.deleteById(id);
	}
}
