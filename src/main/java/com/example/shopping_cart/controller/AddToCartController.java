package com.example.shopping_cart.controller;

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

import com.example.shopping_cart.dto.AddToCartInterface;
import com.example.shopping_cart.entity.AddToCart;
import com.example.shopping_cart.service.AddToCartService;

@RestController
@RequestMapping("/api/cart")
public class AddToCartController {

	@Autowired
	private AddToCartService addToCartService;

	@PostMapping(value = "/create", produces = "application/json")
	public void createCart(@RequestBody AddToCart addToCart) {
		addToCartService.createCart(addToCart);
	}

	@GetMapping(value = "/getById/{customerId}", produces = "application/json")
	public AddToCartInterface getById(@PathVariable("customerId") Integer customerId) {
		AddToCartInterface response = addToCartService.getByCustomerId(customerId);
		return response;
	}

	@GetMapping(value = "/getAll", produces = "application/json")
	public List<AddToCart> getAll() {
		List<AddToCart> list = addToCartService.getAll();
		return list;
	}

	@PutMapping(value = "/update", produces = "application/json")
	public void update(@RequestBody AddToCart addToCart) {
		addToCartService.createCart(addToCart);
	}

	@DeleteMapping(value = "/delete/{id}", produces = "application/json")
	public void delete(@PathVariable("id") Integer id) {
		addToCartService.deleteById(id);
	}

}
