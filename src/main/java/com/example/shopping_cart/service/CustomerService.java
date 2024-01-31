package com.example.shopping_cart.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.example.shopping_cart.dto.CustomerDto;
import com.example.shopping_cart.entity.Customer;
import com.example.shopping_cart.entity.User;
import com.example.shopping_cart.repository.CustomerRepository;
import com.example.shopping_cart.repository.UserRepository;
import com.example.shopping_cart.util.PasswordUtil;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserRepository userRepository;

	public void createCustomer(CustomerDto customerDto) {

		String enpassword = PasswordUtil.getEncryptedPassword(customerDto.getPassword());
		Customer customer = new Customer();
		customer.setName(customerDto.getName());
		customer.setEmail(customerDto.getEmail());
		customer.setPassword(enpassword);
		customer.setPhoneNo(customerDto.getPhoneNo());
		customerRepository.saveAndFlush(customer);
		User user = new User();
		Optional<User> obj = userRepository.findByCustomerId(customerDto.getId());
		if (!obj.isPresent()) {
			user.setCustomerId(customer.getId());
			user.setEmail(customerDto.getEmail());
			user.setUserName(customerDto.getName());
			user.setPassword(enpassword);
			userRepository.save(user);
		}
	}

	public CustomerDto getById(Integer id) {

		Optional<Customer> obj = customerRepository.findById(id);
		if (obj.isPresent()) {
			Customer obj2 = obj.get();
			CustomerDto obj3 = new CustomerDto();
			obj3.setId(obj2.getId());
			obj3.setName(obj2.getName());
			obj3.setEmail(obj2.getEmail());
			obj3.setPhoneNo(obj2.getPhoneNo());
			obj3.setPassword(obj2.getPassword());
			return obj3;
		} else {
			return null;
		}
	}

	public List<CustomerDto> getAll() {
		List<Customer> customerList = customerRepository.findAll();
		List<CustomerDto> customerDtoList = customerList.stream().map(customer -> {
			CustomerDto customerDto = new CustomerDto();
			customerDto.setId(customer.getId());
			customerDto.setName(customer.getName());
			customerDto.setEmail(customer.getEmail());
			customerDto.setPhoneNo(customer.getPhoneNo());
			customerDto.setPassword(customer.getPassword());
			return customerDto;
		}).collect(Collectors.toList());
		return customerDtoList;
	}

	public void deleteById(Integer id) {
		customerRepository.deleteById(id);
	}
	
	public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
		String filepath = "C:\\springboot project\\shopping_cart\\src\\main\\resources\\customer.jrxml";
		String path = "C:\\springboot project\\shopping_cart";
		List<Customer> customer = customerRepository.findAll();

		File file = ResourceUtils.getFile(filepath);

		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customer);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("createdBy", "Dhivya");

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		if (reportFormat.equalsIgnoreCase("html")) {
			JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\customer.html");
		}
		if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\customer.pdf");
		}

		return "report generated in path : " + path;
	}
}