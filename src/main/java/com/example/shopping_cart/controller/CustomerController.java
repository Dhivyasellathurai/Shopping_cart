package com.example.shopping_cart.controller;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping_cart.dto.CustomerDto;
import com.example.shopping_cart.service.CustomerService;

import io.swagger.annotations.Api;
import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping("/api/customer")
@Api(value = "customer Rest API", produces = "application/json", consumes = "application/json")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/create", produces = "application/json")
	public void create(@RequestBody CustomerDto customerDtoRequest) {
		try {
			customerService.createCustomer(customerDtoRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping(value = "/getById/{id}", produces = "application/json")
	public CustomerDto getById(@PathVariable("id") Integer id) {
		return customerService.getById(id);
	}

	@GetMapping(value = "/getAll", produces = "application/json")
	public List<CustomerDto> getAll() {
		return customerService.getAll();
	}

	@PutMapping(value = "/update", produces = "application/json")
	public void update(@RequestBody CustomerDto customerDto) {
		customerService.createCustomer(customerDto);
	}

	@DeleteMapping(value="/delete/{id}",produces = "application/json")
	public void deleteById(@PathVariable("id") Integer id) {
		customerService.deleteById(id);
	}
	
	@GetMapping("/report/{format}")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
		return customerService.exportReport(format);
	}

}
