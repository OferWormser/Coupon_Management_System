package spring.jpa.CouponSystem.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import spring.jpa.CouponSystem.entities.Company;
import spring.jpa.CouponSystem.entities.Customer;
import spring.jpa.CouponSystem.exceptions.LoginException;
import spring.jpa.CouponSystem.exceptions.ObjectAlreadyExistsException;
import spring.jpa.CouponSystem.exceptions.ObjectNotFoundException;
import spring.jpa.CouponSystem.repositories.CompanyRepo;
import spring.jpa.CouponSystem.repositories.CouponRepo;
import spring.jpa.CouponSystem.repositories.CustomerRepo;

@Service
@Validated
public class AdminServiceImpl implements AdminService {

	//ATTRIBUTES	
	@Autowired
	private CompanyRepo companyRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private CouponRepo couponRepo;
	
	
	//METHODS 	
	/**
	 * This method creates a company in the system 
	 * Throws an exception if the company already exists 
	 * @param The company to create
	 */	
	@Override
	@Transactional
	public void createCompany(@Valid Company company) throws ObjectAlreadyExistsException {
		Company check = companyRepo.findCompanyByCompName(company.getCompName());
		if (check != null) 
			throw new ObjectAlreadyExistsException("Company name already exists!", company.getCompName());
		companyRepo.save(company);
	}
	
	/**
	 * This method deletes a company and all its coupons from the system
	 * Throws an exception if the company wasn't found 
	 * @param The company id to remove the company
	 */
	@Override
	@Transactional
	public void removeCompany(@Positive int companyId) throws ObjectNotFoundException {
        Optional<Company> optionalCompany = companyRepo.findById(companyId);
        if ( !optionalCompany.isPresent() )
			throw new ObjectNotFoundException("Company with id: " + companyId + " not found!", "removeCompany()");
		companyRepo.removeAllCompanyCoupons(companyId);;
		companyRepo.deleteById(companyId);
	}

	/**
	 * This method updates a company's email and password in the system 
	 * Throws an exception if the company wasn't found
	 * @param The company to update
	 */
	@Override
	@Transactional
	public void updateCompany(@Valid Company company) throws ObjectNotFoundException {
		Company check = companyRepo.findCompanyByCompName(company.getCompName());
		if (check == null) 
			throw new ObjectNotFoundException("Company: " + company.getCompName() + " not found!", "updateCompany()");
		companyRepo.updateCompany(company.getPassword(), company.getEmail(), check.getId());
	}

	/**
	 * This method returns a company information by id from the system 
	 * Throws an exception if the company wasn't found
	 * @param The company id to get information
	 */
	@Override
	public Company getCompany(@Positive int companyId) throws ObjectNotFoundException {
        Optional<Company> optionalCompany = companyRepo.findById(companyId);
        if ( !optionalCompany.isPresent() )
			throw new ObjectNotFoundException("Company with id: " + companyId + " not found!", "getCompany()");
		return optionalCompany.get();
	}

	/**
	 * This method returns all companies from the system to array list
	 * Throws an exception if the companies weren't found

	 */
	@Override
	public List<Company> getAllCompanies() throws ObjectNotFoundException {
		ArrayList<Company> companies = (ArrayList<Company>) companyRepo.findAll();
		if (companies == null)
			throw new ObjectNotFoundException("Error getting all companies!", "getAllCompanies()");
		return companies;
	}

	/**
	 * This method creates a customer in the system
	 * Throws an exception if the customer already exists
	 * @param The customer to create
	 */
	@Override
	@Transactional
	public void createCustomer(@Valid Customer customer) throws ObjectAlreadyExistsException {
		Customer check = customerRepo.findCustomerByCustName(customer.getCustName());
		if (check != null) 
			throw new ObjectAlreadyExistsException("Customer name already exists!", customer.getCustName());
		customerRepo.save( customer );
	}

	/**
	 * This method deletes a customer and all his purchased coupons from the system 
	 * Throws an exception if the customer wasn't found
	 * @param The customer id to remove the customer
	 */
	@Override
	@Transactional
	public void removeCustomer(@Positive int customerId) throws ObjectNotFoundException {
        Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
        if ( !optionalCustomer.isPresent() )
			throw new ObjectNotFoundException("Customer with id: " + customerId + " not found!", "removeCustomer()");
		customerRepo.deleteById(customerId);
	}

	/**
	 * This method updates a customers password in the system 
	 * @param The customer to update
	 * Throws an exception if the customer wasn't found
	 */
	@Override
	@Transactional
	public void updateCustomer(@Valid Customer customer) throws ObjectNotFoundException {
		Customer check = customerRepo.findCustomerByCustName(customer.getCustName());
		if (check == null) 
			throw new ObjectNotFoundException("Customer: " + customer.getCustName() + " not found!", "updateCustomer()");
		customerRepo.updateCustomer(customer.getPassword(), check.getId());

	}

	/**
	 * This method returns a customer information by id from the system 
	 * Throws an exception if the customer wasn't found
	 * @param The customer id to get information
	 */
	@Override
	public Customer getCustomer(@Positive int customerId) throws ObjectNotFoundException {
        Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
        if ( !optionalCustomer.isPresent() )
			throw new ObjectNotFoundException("Customer with id: " + customerId + " not found", "getCustomer()");
		return optionalCustomer.get();
	}
	
	/**
	 * This method returns all customers from the system to array list
	 * Throws an exception if the customers weren't found
	 */
	@Override
	public List<Customer> getAllCustomers() throws ObjectNotFoundException {
		ArrayList<Customer> customers = (ArrayList<Customer>) customerRepo.findAll();
		if (customers == null)
			throw new ObjectNotFoundException("Error getting all customers!", "getAllCustomers()");
		return customers;
	}

	/**
	 * This method logs in a admin client
	 * Throws an exception if the admin login details are wrong 
	 * @param The admin's user name
	 * @param The admin's password
	 * @param The admin's client type
	 */	
	@Override
	public boolean login(@NotNull @NotBlank String name, @NotNull @NotBlank String password,
			@NotNull ClientType type) throws LoginException {
		if(!type.equals(ClientType.ADMIN))
			throw new LoginException("Login failed!", "login()");
		if(!name.equals("admin") && password.equals("1234"))
			throw new LoginException("Login failed!", "login()");
		return true;
	}
	
	/**
	 * This method removes all expired coupons every 24 hours 
	 */	
	@Override
	@Scheduled(fixedRateString = "${admin.notification.schedule.milliseconds}")
	    public void removeExpiredCoupons() {
			couponRepo.removeExpiredCoupons(new Date(System.currentTimeMillis()));
	    }
}
