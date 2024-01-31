package com.example.shopping_cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping_cart.entity.Products;
import com.example.shopping_cart.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public void createProduct(Products request) {
		productRepository.saveAndFlush(request);

	}

	public Optional<Products> getById(Integer id) {
		return productRepository.findById(id);
	}

	public List<Products> getAll() {
		return productRepository.findAll();
	}

	public void deleteById(Integer id) {
		productRepository.deleteById(id);
	}

}
