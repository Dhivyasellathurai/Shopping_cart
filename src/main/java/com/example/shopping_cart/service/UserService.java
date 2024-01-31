package com.example.shopping_cart.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.shopping_cart.entity.User;
import com.example.shopping_cart.repository.UserRepository;
import com.example.shopping_cart.util.PasswordUtil;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepository.findByUserName(username);
		if (!userOptional.isPresent()) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		User user = userOptional.get();
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				getAuthority());
	}

	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	public void create(User request) {
		request.setPassword(PasswordUtil.getEncryptedPassword(request.getPassword()));
		userRepository.save(request);
	}

	public Optional<User> getById(Integer id) {
		return userRepository.findById(id);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public void update(User request) {
		userRepository.saveAndFlush(request);
	}

	public void delete(Integer id) {
		userRepository.deleteById(id);
	}

}