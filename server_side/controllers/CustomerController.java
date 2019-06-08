package spring.jpa.CouponSystem.controllers;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.jpa.CouponSystem.entities.Coupon;
import spring.jpa.CouponSystem.entities.CouponType;
import spring.jpa.CouponSystem.entities.LoggedClient;
import spring.jpa.CouponSystem.exceptions.CouponSystemException;
import spring.jpa.CouponSystem.exceptions.ObjectNotFoundException;
import spring.jpa.CouponSystem.services.CustomerServiceImpl;

@RestController
@RequestMapping("rest/customer")
public class CustomerController {

	//ATTRIBUTES
	@Autowired
	private CustomerServiceImpl cs;

	//METHODS
	/**
	 * This method gets the logged client's details from the  session 
	 */	
	private LoggedClient getLoggedClient (HttpServletRequest request) {
		return (LoggedClient) request.getSession(false).getAttribute("loggedClient");
	}

	/**
	 * This method receives a coupon's id to purchase from the client
	 * and adds the wanted coupon by id to the customer by id 
	 */	
	@PostMapping(path = "/{couponId}")
	public void purchaseCoupon(@PathVariable int couponId, HttpServletRequest request) throws CouponSystemException {
		cs.purchaseCoupon(couponId, getLoggedClient(request).getId());
	}
	
	/**
	 * This method receives a coupon's id from the client
	 * and returns the coupon's information back by the coupon's id and the customer's id
	 */	
	@GetMapping(path = "coupons/{couponId}")
	public Coupon getPurchasedCoupon(@PathVariable int couponId, HttpServletRequest request) throws CouponSystemException {
		Coupon coupon = cs.getPurchasedCoupon(couponId, getLoggedClient(request).getId());
		return coupon;		
	}
	
	/**
	 * This method returns all purchased coupons by the client's id
	 */	
	@GetMapping(path = "purchasedCoupons")
	public List<Coupon> getAllPurchasedCoupons(HttpServletRequest request) throws CouponSystemException {
		List<Coupon> coupons = cs.getAllPurchasedCoupons(getLoggedClient(request).getId());
		return coupons;
	}

	/**
	 * This method receives a coupon's type from the client
	 * and method returns all purchased coupons by the coupon's type and by the client's id
	 */	
	@GetMapping(path = "coupons/byType/{type}")
	public List<Coupon> getPurchasedCouponsByType(@PathVariable String type, HttpServletRequest request) throws ObjectNotFoundException {
		List<Coupon> coupons = cs.getPurchasedCouponsByType(CouponType.valueOf(type.toUpperCase()), getLoggedClient(request).getId());
		return coupons;
	}

	/**
	 * This method receives a coupon's price from the client
	 * and returns all purchased coupons by the coupon's price and by the client's id
	 */	
	@GetMapping(path = "coupons/byPrice/{price}")
	public List<Coupon> getPurchasedCouponsByPrice(@PathVariable double price, HttpServletRequest request) throws ObjectNotFoundException {
		List<Coupon> coupons = cs.getPurchasedCouponsByPrice(price, getLoggedClient(request).getId());
		return coupons;
	}

	/**
	 * This method receives a coupon's end date from the client
	 * and returns all purchased coupons by the coupon's end date and by the client's id
	 */	
	@GetMapping(path = "coupons/byDate/{endDate}")
	public List<Coupon> getPurchasedCouponsByDate(@PathVariable Date endDate, HttpServletRequest request) throws CouponSystemException {
		List<Coupon> coupons = cs.getPurchasedCouponsByDate(endDate, getLoggedClient(request).getId());
		return coupons;
	}
	
	/**
	 * This method returns all existing coupons in the system
	 */	
	@GetMapping(path = "coupons")
	public List<Coupon> getAllCoupons() throws ObjectNotFoundException {
		List<Coupon> coupons = cs.getAllCoupons();
		return coupons;
	}


}
