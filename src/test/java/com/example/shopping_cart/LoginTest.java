package com.example.shopping_cart;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.shopping_cart.entity.LoginRequest;
import com.example.shopping_cart.entity.User;
import com.example.shopping_cart.repository.UserRepository;
import com.example.shopping_cart.service.LoginService;
import com.example.shopping_cart.util.JwtTokenUtill;

import lombok.var;

@ExtendWith(MockitoExtension.class)
public class LoginTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	LoginService loginService;

	@Mock
	private JwtTokenUtill jwtTokenUtil;

	@Test
	void validLoginTest() {

		User user = new User(1, 1, "dhivya@gmail.com", "Dhivya", "e6adae477c60f8bd9d3b5289b57f988cc78cd4cd");
		when(userRepository.findByUserName("Dhivya")).thenReturn(Optional.of(user));
		LoginRequest request = new LoginRequest();
		request.setUserName("Dhivya");
		request.setPassword("dhivya123");
		var response = loginService.login(request);
		assertNotNull(response);
		assertTrue(response.containsKey("status"));
		assertTrue(response.containsKey("message"));
		assertTrue(response.containsKey("jwt"));
		assertTrue(response.containsKey("userId"));
	}

}
