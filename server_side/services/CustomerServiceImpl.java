package spring.jpa.CouponSystem.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import spring.jpa.CouponSystem.entities.Coupon;
import spring.jpa.CouponSystem.entities.CouponType;
import spring.jpa.CouponSystem.entities.Customer;
import spring.jpa.CouponSystem.exceptions.CouponSystemException;
import spring.jpa.CouponSystem.exceptions.LoginException;
import spring.jpa.CouponSystem.exceptions.ObjectAlreadyExistsException;
import spring.jpa.CouponSystem.exceptions.ObjectNotFoundException;
import spring.jpa.CouponSystem.repositories.CouponRepo;
import spring.jpa.CouponSystem.repositories.CustomerRepo;

@Service
@Validated
public class CustomerServiceImpl implements CustomerService {
	
	//ATTRIBUTES
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private CouponRepo couponRepo;
	
	//METHODS	
	/**
	 * This method adds a coupon to a customer after purchase and reduces the amount in stock 
	 * Throws exceptions if the coupon: not found / already purchased / expired / not in stock
	 * @param The coupon's id that a customer wants to purchase
	 * @param The customer's id that wants to purchase the coupon
	 */
	@Override
	@Transactional
	public synchronized void purchaseCoupon(@Positive int couponId, @Positive int customerId) throws CouponSystemException {
        Optional<Coupon> optionalCoupon = couponRepo.findById(couponId);
        if ( !optionalCoupon.isPresent() )
			throw new ObjectNotFoundException("Coupon with id: " + couponId + " not found!", "purchaseCoupon()");
 		Coupon coupon = optionalCoupon.get();
		Coupon customerCoupon = customerRepo.getCustomerCoupon(couponId, customerId);
		if (customerCoupon != null)
			throw new ObjectAlreadyExistsException("Coupon already purchased!", customerCoupon.getTitle());
		if (coupon.getAmount() <= 0)
			throw new CouponSystemException("Coupon sold out!", coupon.getTitle());
		if (coupon.getEndDate().before(new Date(System.currentTimeMillis())))
			throw new CouponSystemException("Coupon expired!", coupon.getTitle());			
		Customer customer = customerRepo.findById(customerId).get();
		customer.addCoupon(coupon);
		coupon.addCustomer(customer);
		coupon.setAmount(coupon.getAmount() -1);
	}

	/**
	 * This method returns a coupon's information from the system by id
	 * Throws an exception if the coupon wasn't found
	 * @param The coupon's id
	 * @param The customer's id that owns the coupon 
	 */	
	@Override
	public Coupon getPurchasedCoupon(@Positive int couponId, @Positive int customerId) throws CouponSystemException{
		Coupon coupon = customerRepo.getCustomerCoupon(couponId, customerId);
		if (coupon == null) 
			throw new ObjectNotFoundException("Coupon with id: " + couponId + " not found!", "getCustomerCoupon()");
		if (coupon.getAmount() <= 0) {
			throw new CouponSystemException("Coupon with id: " + couponId + " sold out!", "getCustomerCoupon()");			
		}
		return coupon;
	}

	/**
	 * This method returns all customer's purchased coupons from the system to array list
	 * Throws an exception if the coupons weren't found
	 * @param The customer's id that owns the coupons 
	 */
	@Override
	public List<Coupon> getAllPurchasedCoupons(@Positive int customerId) throws ObjectNotFoundException {
		ArrayList<Coupon> customerCoupons = (ArrayList<Coupon>) customerRepo.getCustomerCoupons(customerId);
		if (customerCoupons == null)
			throw new ObjectNotFoundException("Coupons not found!", "getCustomerCoupons()");
		return customerCoupons;
	}

	/**
	 * This method returns customer's purchased coupons by type from the system to array list
	 * Throws an exception if the coupons weren't found
	 * @param The coupon type as a filter
	 * @param The customer's id that owns the coupons 
	 */
	@Override
	public List<Coupon> getPurchasedCouponsByType(@NotNull CouponType couponType, @Positive int customerId) throws ObjectNotFoundException {
		ArrayList<Coupon> customerCoupons = (ArrayList<Coupon>) 
				customerRepo.getCustomerCouponsByType(couponType, customerId);
		if (customerCoupons == null)
			throw new ObjectNotFoundException("Coupons not found!", "getCustomerCouponsByType()");
		return customerCoupons;	
	}

	/**
	 * This method returns company's coupons by maximum price from the system to array list 
	 * Throws an exception if the coupons weren't found
	 * @param The coupon price as a filter
	 * @param The customer's id that owns the coupons 
	 */
	@Override
	public List<Coupon> getPurchasedCouponsByPrice(@Positive double couponPrice, @Positive int customerId) throws ObjectNotFoundException {
		ArrayList<Coupon> customerCoupons = (ArrayList<Coupon>) 
				customerRepo.getCustomerCouponsByPrice(couponPrice, customerId);
		if (customerCoupons == null)
			throw new ObjectNotFoundException("Coupons not found!", "getCustomerCouponsByPrice()");
		return customerCoupons;	
	}

	/**
	 * This method returns customer's coupons by coupons end date from the system to array list 
	 * Throws an exception if the coupons weren't found
	 * @param The coupon price as a filter
	 * @param The customer's id that owns the coupons 
	 */
	@Override
	public List<Coupon> getPurchasedCouponsByDate(@NotNull Date couponEndDate, @Positive int customerId) throws CouponSystemException {
		if (couponEndDate.before(new Date(System.currentTimeMillis()))) {
			throw new CouponSystemException("The date has expired!", "getCustomerCouponsByDate()");
		}
		ArrayList<Coupon> coupons = (ArrayList<Coupon>) 
				customerRepo.getCustomerCouponsByDate(couponEndDate, customerId);
		if (coupons == null)
			throw new ObjectNotFoundException("Coupons not found!", "getCustomerCouponsByDate()");
		return coupons;	
	}

	/**
	 * This method returns all existing coupons in the system to array list
	 * Throws an exception if the coupons weren't found
	 */	
	public List<Coupon> getAllCoupons() throws ObjectNotFoundException {
		ArrayList<Coupon> coupons = (ArrayList<Coupon>) 
				couponRepo.findAll();
		if (coupons == null)
			throw new ObjectNotFoundException("Coupons not found!", "getCustomerCouponsByDate()");
		return coupons;			
	}

	/**
	 * This method logs a customer user to the system by name,password and client type and returns the customer's information 
	 * Throws an exception if the customer's login details are wrong 
	 * @param The customer user name to log in
 	 * @param The customer user password to log in
 	 * @param The client type to log in
	 */
	@Override
	public Customer login(@NotNull @NotBlank String name, @NotNull @NotBlank String password,
			@NotNull ClientType type) throws LoginException {
		if (!type.equals(ClientType.CUSTOMER))
			throw new LoginException("Login failed!", "login()");
		Customer loggedIn = customerRepo.findCustomerByCustNameAndPassword(name, password);
		if (loggedIn == null)
			throw new LoginException("Login failed!", "login()");
		return loggedIn;
	}

}
