package com.example.shopping_cart.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping_cart.entity.User;
import com.example.shopping_cart.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user")
@Api(value = "customer Rest API", produces = "application/json", consumes = "application/json")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/create", produces = "application/json")
	@ApiOperation(value = "Allows to create user.")
	public void create(@RequestBody User request) {
		userService.create(request);
	}

	@GetMapping(value = "/getById/{id}", produces = "application/json")
	public Optional<User> getById(@PathVariable("id") Integer id) {
		return userService.getById(id);
	}

	@GetMapping(value = "/getAll", produces = "application/json")
	public List<User> getAll() {
		return userService.getAll();

	}

	@PutMapping(value = "/update", produces = "application/json")
	public void update(@RequestBody User request) {
		userService.update(request);
	}

	@DeleteMapping(value = "/delete/{id}", produces = "application/json")
	public void deleteById(@PathVariable("id") Integer id) {
		userService.delete(id);
	}

}
