package com.kindsonthegenius.fleetapp.security.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kindsonthegenius.fleetapp.models.User;
import com.kindsonthegenius.fleetapp.repositories.UserRepository;
import com.kindsonthegenius.fleetapp.security.models.Role;
import com.kindsonthegenius.fleetapp.security.repositories.RoleRepository;
//import com.kindsonthegenius.fleetapp.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired private RoleRepository roleRepository;
	@Autowired private UserRepository userRepository;
	
	//Returns the list of roles from the database
	public List<Role> getRoles(){
		return roleRepository.findAll();
	}
	
	//Save new role to the database
	public void save(Role role) {
		roleRepository.save(role);
	}
	
	//get by id
	public Optional<Role> findById(int id) {
		Optional<Role> role = roleRepository.findById(id);
	    if (role.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role with id %d not found", id));
	    }
	    
	    return role;
	}

	//Delete a role by id
	public void delete(int id) {
		roleRepository.deleteById(id);
	}
	
	//Assign Role to User
	public void assignUserRole(Integer userId, Integer roleId){
	    User user  = userRepository.findById(userId).orElse(null);
	    Role role = roleRepository.findById(roleId).orElse(null);
	   Set<Role> userRoles = user.getRoles();
	   userRoles.add(role);
	   user.setRoles(userRoles);
	   userRepository.save(user);
	}
	
	//Unassign Role to User
	public void unassignUserRole(Integer userId, Integer roleId){
	    User user  = userRepository.findById(userId).orElse(null);
	    user.getRoles().removeIf(x -> x.getId()==roleId);
	    userRepository.save(user);
	}
	
	public Set<Role> getUserRoles(User user){
	    return user.getRoles();
	}
	
	public Set<Role> getUserNotRoles(User user){
		   return roleRepository.getUserNotRoles(user.getId());
	}
}
