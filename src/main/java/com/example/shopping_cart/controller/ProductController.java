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

import com.example.shopping_cart.entity.Products;
import com.example.shopping_cart.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping(value = "/create", produces = "application/json")
	public void createProduct(@RequestBody Products request) {
		productService.createProduct(request);
	}

	@GetMapping(value = "/getById/{id}", produces = "application/json")
	public Optional<Products> getById(@PathVariable("id") Integer id) {
		return productService.getById(id);
	}

	@GetMapping(value = "/getAll", produces = "application/json")
	public List<Products> getAll() {
		return productService.getAll();
	}

	@PutMapping(value = "/update", produces = "application/json")
	public void update(@RequestBody Products request) {
		productService.createProduct(request);
	}

	@DeleteMapping(value = "/delete/{id}", produces = "application/json")
	public void deleteById(@PathVariable("id") Integer id) {
		productService.deleteById(id);
	}
}
