package com.example.shopping_cart.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping_cart.entity.LoginRequest;
import com.example.shopping_cart.service.LoginService;

@RestController
@RequestMapping("/userLogin")
public class LoginController {

	@Autowired
	LoginService loginService;

	@GetMapping(value = "/login", produces = "application/json")
	public Map<String, Object> login(@RequestBody LoginRequest request) {
		return loginService.login(request);
	}

}
