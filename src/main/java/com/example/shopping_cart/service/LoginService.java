package com.example.shopping_cart.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping_cart.entity.Errors;
import com.example.shopping_cart.entity.LoginRequest;
import com.example.shopping_cart.entity.User;
import com.example.shopping_cart.repository.UserRepository;
import com.example.shopping_cart.util.JwtTokenUtill;
import com.example.shopping_cart.util.PasswordUtil;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtTokenUtill jwtTokenUtil;

	public Map<String, Object> login( LoginRequest request) {
		Map<String, Object> response = new HashMap<String, Object>();
		Errors err = null;
		if (request == null) {
			err = new Errors();
			err.setCode("400");
			err.setMessage("Invalid incredintials");
			response.put("error", err);
			return response;
		}

		Optional<User> user = userRepository.findByUserName(request.getUserName());
		if (!user.isPresent()) {
			err = new Errors();
			err.setCode("400");
			err.setMessage("Invalid Username");
			response.put("status", 0);
			response.put("message", err);
			return response;
		}
		User user1 = user.get();
		String password = PasswordUtil.getEncryptedPassword(request.getPassword());
		if (!user1.getPassword().equals(password)) {
			err = new Errors();
			err.setCode("400");
			err.setMessage("Password is wrong.!");
			response.put("status", 0);
			response.put("error", err);
			return response;
		}
		final String token = jwtTokenUtil.generateToken(user1);
		response.put("status", 1);
		response.put("message", "Logged in successfully.!");
		response.put("jwt", token);
		response.put("userId", user1.getId());
		return response;
	}
}
