package com.example.shopping_cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping_cart.dto.AddToCartInterface;
import com.example.shopping_cart.entity.AddToCart;
import com.example.shopping_cart.entity.Products;
import com.example.shopping_cart.repository.AddToCartRepository;
import com.example.shopping_cart.repository.ProductRepository;

@Service
public class AddToCartService {

	@Autowired
	private AddToCartRepository addToCartRepository;
	@Autowired
	private ProductRepository productRepository;

	public void createCart(AddToCart request) {
		Optional<Products> product = productRepository.findById(request.getProductId());
		Optional<AddToCart> addOptional = addToCartRepository.findProduct(request.getProductId(),
				request.getCustomerId());
		Products productDetails = product.get();

		if (addOptional.isPresent()) {
			AddToCart cart = addOptional.get();
			int quantity = cart.getQuantity() + request.getQuantity();
			cart.setQuantity(quantity);
			cart.setTotalAmount(quantity * productDetails.getPrice());
			addToCartRepository.saveAndFlush(cart);

		} else {
			request.setProductPrice(productDetails.getPrice());
			request.setTotalAmount(request.getQuantity() * request.getProductPrice());
			addToCartRepository.saveAndFlush(request);
		}
		if (productDetails.getAvailableStock() > request.getQuantity()) {
			productDetails.setAvailableStock(productDetails.getAvailableStock() - request.getQuantity());
			productRepository.save(productDetails);
		}
	}

	public AddToCartInterface getByCustomerId(Integer customerId) {
		return addToCartRepository.findByCustomerId(customerId);
	}

	public List<AddToCart> getAll() {
		return addToCartRepository.findAll();
	}

	public void deleteById(Integer id) {
		addToCartRepository.deleteById(id);

	}
}
