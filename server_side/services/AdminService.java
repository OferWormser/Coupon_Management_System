package spring.jpa.CouponSystem.services;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import spring.jpa.CouponSystem.entities.Company;
import spring.jpa.CouponSystem.entities.Customer;
import spring.jpa.CouponSystem.exceptions.LoginException;
import spring.jpa.CouponSystem.exceptions.ObjectAlreadyExistsException;
import spring.jpa.CouponSystem.exceptions.ObjectNotFoundException;

public interface AdminService {

	//METHODS
	/**
	 * This method creates a company
	 * @param The company to create
	 */
	void createCompany(@Valid Company company) throws ObjectAlreadyExistsException;
	/**
	 * This method deletes a company
	 * @param The company to remove
	 */
	void removeCompany(@Positive int companyId) throws ObjectNotFoundException;
	/**
	 * This method updates a company information
	 * @param The company to update
	 */
	void updateCompany(@Valid Company company) throws ObjectNotFoundException;
	/**
	 * This method returns a company information by id 
	 * @param The company id to get information
	 */
	Company getCompany(@Positive int companyId) throws ObjectNotFoundException;
	/**
	 * This method returns all companies to array list
	 */
	List<Company> getAllCompanies() throws ObjectNotFoundException;
	/**
	 * This method creates a customer  
	 * @param The customer to create
	 */
	void createCustomer(@Valid Customer customer) throws ObjectAlreadyExistsException;
	/**
	 * This method deletes a customer
	 * @param The customer to remove
	 */
	void removeCustomer(@Positive int customerId) throws ObjectNotFoundException;
	/**
	 * This method updates a customer  
	 * @param The customer to update
	 */
	void updateCustomer(@Valid Customer customer) throws ObjectNotFoundException;
	/**
	 * This method returns a customer information by id 
	 * @param The customer id to get information
	 */
	Customer getCustomer(@Positive int customerId) throws ObjectNotFoundException;
	/**
	 * This method returns all customers to array list
	 */	
	List<Customer> getAllCustomers() throws ObjectNotFoundException;

	/**
	 * This method logs in a admin client and returns true / false
	 * @param The admin's user name
	 * @param The admin's password
	 * @param The admin's client type
	 */	
	boolean login(@NotNull @NotBlank String name, @NotNull @NotBlank String password,
			@NotNull ClientType type) throws LoginException;

	/**
	 * This method removes all expired coupons 
	 */	
	void removeExpiredCoupons();
	
}
