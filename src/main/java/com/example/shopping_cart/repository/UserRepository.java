package com.example.shopping_cart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shopping_cart.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserName(String userName);

	Optional<User> findByCustomerId(int customerId);

}
