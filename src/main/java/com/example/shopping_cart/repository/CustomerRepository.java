package com.example.shopping_cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping_cart.entity.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
