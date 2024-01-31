package com.example.shopping_cart.dto;

public interface AddToCartInterface {

	Integer getId();

	String getProductName();

	String getCustomerName();

	Integer getQuantity();

	Double getProductPrice();

	Double getTotalAmount();

}