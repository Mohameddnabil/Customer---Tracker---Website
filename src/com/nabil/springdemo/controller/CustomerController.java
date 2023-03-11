package com.nabil.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nabil.springdemo.entity.Customer;
import com.nabil.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/list")
	public String listCustomer(Model theModel) {
		
		List<Customer>theCustomers = customerService.getCustomers();
		theModel.addAttribute("customers",theCustomers);
		
		return "list-customer";
	}
	
	@GetMapping("/showFormForAdd")
	public String processForm(Model theModel) {
		Customer customer = new Customer();
		theModel.addAttribute("customer",customer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer){		
		customerService.addCustomer(theCustomer);
		return "redirect:/customer/list";
	}
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int id, Model model) {
		Customer theCustomer = customerService.getCustomer(id);
		model.addAttribute("customer",theCustomer);
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		customerService.deleteCustomer(theId);
		return "redirect:/customer/list";
	}
}
