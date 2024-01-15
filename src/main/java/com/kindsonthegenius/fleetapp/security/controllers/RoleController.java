package com.kindsonthegenius.fleetapp.security.controllers;

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

import com.kindsonthegenius.fleetapp.models.User;
import com.kindsonthegenius.fleetapp.security.models.Role;
import com.kindsonthegenius.fleetapp.security.services.RoleService;
import com.kindsonthegenius.fleetapp.services.UserService;

//import com.kindsonthegenius.fleetapp.models.Role;
//import com.kindsonthegenius.fleetapp.services.RoleService;

@Controller
public class RoleController {

@Autowired private RoleService roleService;
@Autowired private UserService userService;
	
	@GetMapping("/roles")
	public String getStates(Model model) {
		List<Role> locationList = roleService.getRoles();
		model.addAttribute("roles", locationList);
		
		return "role";
	}
	
	@PostMapping("/roles/addNew")
	public String addNew(Role role) {
		roleService.save(role);
		return "redirect:/roles";
	}
	
	
	
	@GetMapping("/roles/findById/{id}")
    @ResponseBody
	public Optional<Role> findById(@PathVariable("id") int id) {
		Optional<Role> role = roleService.findById(id);
	    if (role.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role with id %d not found", id));
	    }

	    return role;
	}
	
	@RequestMapping(value="/roles/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(Role role) {
		roleService.save(role);
		return "redirect:/roles";
	}
	
	@RequestMapping(value="/roles/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		roleService.delete(id);
		return "redirect:/roles";
	}
	
	@GetMapping("/security/user/Edit/{id}")
	public String editUser(@PathVariable("id") Integer id, Model model){
	    User user = userService.findById(id);
	    model.addAttribute("user", user);
	    model.addAttribute("userRoles", roleService.getUserRoles(user));
	    model.addAttribute("userNotRoles", roleService.getUserNotRoles(user));
	    return "/userEdit";
	}
	
	@RequestMapping("/security/role/assign/{userId}/{roleId}")
	public String assignRole(@PathVariable("userId") Integer userId, 
	                         @PathVariable("roleId") Integer roleId){
	    roleService.assignUserRole(userId, roleId);
	    return "redirect:/security/user/Edit/"+userId;
	}
	
	@RequestMapping("/security/role/unassign/{userId}/{roleId}")
	public String unassignRole(@PathVariable("userId") Integer userId,
	                           @PathVariable("roleId") Integer roleId){
	    roleService.unassignUserRole(userId, roleId);
	    return "redirect:/security/user/Edit/"+userId;
	}
}
