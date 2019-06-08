package spring.jpa.CouponSystem.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.jpa.CouponSystem.entities.Company;
import spring.jpa.CouponSystem.entities.Customer;
import spring.jpa.CouponSystem.exceptions.CouponSystemException;
import spring.jpa.CouponSystem.services.AdminServiceImpl;

@RestController
@RequestMapping("rest/admin")
public class AdminController {

	//ATTRIBUTES
	@Autowired
	private AdminServiceImpl as;

	//METHODS
	/**
	 * This method receives a new company's information to create from the client
	 * Creates the new company in the system by the company's information
	 * Returns the company's information back to the client 
	 */		
	@PostMapping(path = "companies")
	public Company createCompany(@RequestBody Company company) throws CouponSystemException {		
		as.createCompany(company);
		return company;
	}
	
	/**
	 * This method receives company's id to delete from the client
	 * Deletes the company from the system by the company's id
	 */	
	@DeleteMapping(path = "companies/{companyId}") 
	public void removeCompany(@PathVariable int companyId) throws CouponSystemException {
		as.removeCompany(companyId);
	}
	
	/**
	 * This method receives a company's information to update from the client
	 * Updates the company in the system by the company's new information
	 * Returns the updated company's information back to the client 
	 */
	@PutMapping(path = "companies")
	public Company updateCompany(@RequestBody Company company) throws CouponSystemException {
		as.updateCompany(company);
		return company;
	}
	
	/**
	 * This method receives company's id from the client 
	 * Getting the company's information by the company's id 
	 * Returns the company's information to the client
	 */	
	@GetMapping(path = "companies/{companyId}")
	public Company getCompany(@PathVariable int companyId) throws CouponSystemException {
		Company company = as.getCompany(companyId);
		return company;
	}
	
	/**
	 * This method returns all existing companies
	 */
	@GetMapping(path = "companies")
	public List<Company> getAllCompanies(HttpServletRequest request) throws CouponSystemException {
		List<Company> companies = as.getAllCompanies();
		return companies;
	}
	
	/**
	 * This method receives a new customer's information to create from the client
	 * Creates the new customer in the system by the customer's information
	 * Returns the customer's information back to the client 
	 */	
	@PostMapping(path = "customers")
	public Customer createCustomer(@RequestBody Customer customer) throws CouponSystemException {
		as.createCustomer(customer);
		return customer;
	}
	
	/**
	 * This method receives customer's id to delete from the client
	 * Deletes the customer from the system by the customer's id
	 */
	@DeleteMapping(path = "customers/{customerId}")
	public void removeCustomer(@PathVariable int customerId) throws CouponSystemException {
		as.removeCustomer(customerId);
	}
	
	/**
	 * This method receives a customer's information to update from the client
	 * Updates the customer in the system by the customer's new information
	 * Returns the updated customer's information back to the client 
	 */
	@PutMapping(path = "customers")
	public Customer updateCustomer(@RequestBody Customer customer) throws CouponSystemException {
		as.updateCustomer(customer);
		return customer;
	}
	
	/**
	 * This method receives customer's id from the client 
	 * Getting the customer's information by the customer's id 
	 * Returns the customer's information to the client
	 */	
	@GetMapping(path = "customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) throws CouponSystemException {
		Customer customer = as.getCustomer(customerId);
		return customer;
	}
	
	/**
	 * This method returns all existing customers
	 */
	@GetMapping(path = "customers")
	public List<Customer> getAllCustomers() throws CouponSystemException {
		List<Customer> customers = as.getAllCustomers();
		return customers;
	}
}
