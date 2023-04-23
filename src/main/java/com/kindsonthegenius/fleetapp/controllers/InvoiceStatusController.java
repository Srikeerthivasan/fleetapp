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

import com.kindsonthegenius.fleetapp.models.InvoiceStatus;
import com.kindsonthegenius.fleetapp.services.InvoiceStatusService;

@Controller
public class InvoiceStatusController {

@Autowired private InvoiceStatusService invoiceStatusService;
	
	@GetMapping("/invoicestatuses")
	public String getStates(Model model) {
		List<InvoiceStatus> locationList = invoiceStatusService.getInvoiceStatuss();
		model.addAttribute("invoicestatuses", locationList);
		
		return "invoicestatus";
	}
	
	@PostMapping("/invoicestatuses/addNew")
	public String addNew(InvoiceStatus invoicestatus) {
		invoiceStatusService.save(invoicestatus);
		return "redirect:/invoicestatuses";
	}
	
	
	
	@GetMapping("/invoicestatuses/findById/{id}")
    @ResponseBody
	public Optional<InvoiceStatus> findById(@PathVariable("id") int id) {
		Optional<InvoiceStatus> invoicestatus = invoiceStatusService.findById(id);
	    if (invoicestatus.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("InvoiceStatus with id %d not found", id));
	    }

	    return invoicestatus;
	}
	
	@RequestMapping(value="/invoicestatuses/update", method = {RequestMethod.PUT, RequestMethod.GET})
	public String update(InvoiceStatus invoicestatus) {
		invoiceStatusService.save(invoicestatus);
		return "redirect:/invoicestatuses";
	}
	
	@RequestMapping(value="/invoicestatuses/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(@PathVariable("id") int id) {
		invoiceStatusService.delete(id);
		return "redirect:/invoicestatuses";
	}
}
