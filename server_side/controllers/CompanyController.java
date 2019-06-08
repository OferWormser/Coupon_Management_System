package spring.jpa.CouponSystem.controllers;

import java.sql.Date;
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

import spring.jpa.CouponSystem.entities.Coupon;
import spring.jpa.CouponSystem.entities.CouponType;
import spring.jpa.CouponSystem.entities.LoggedClient;
import spring.jpa.CouponSystem.exceptions.CouponSystemException;
import spring.jpa.CouponSystem.exceptions.ObjectNotFoundException;
import spring.jpa.CouponSystem.services.CompanyServiceImpl;

@RestController
@RequestMapping("rest/company")
public class CompanyController {
	
	//ATTRIBUTES
	@Autowired
	private CompanyServiceImpl cs;

	
	//METHODS
	/**
	 * This method gets the logged client's details from the  session 
	 */	
	private LoggedClient getLoggedClient (HttpServletRequest request) {
		return (LoggedClient) request.getSession(false).getAttribute("loggedClient");
	}
	
	/**
	 * This method receives a new coupon's information to create from the client
	 * Creates the new coupon in the system by the coupon's information and by the client's id
	 * Returns the coupon's information back to the client 
	 */	
	@PostMapping(path = "")
	public Coupon createCoupon(@RequestBody Coupon coupon, HttpServletRequest request) throws CouponSystemException {		
		cs.createCoupon(coupon, getLoggedClient(request).getId());
		return coupon;
	}

	/**
	 * This method receives coupon's id to delete from the client
	 * Deletes the coupon from the system by the coupon's id and by the client's id
	 */	
	@DeleteMapping(path = "coupons/{couponId}")
	public void removeCoupon(@PathVariable int couponId, HttpServletRequest request) throws ObjectNotFoundException {
		cs.removeCoupon(couponId, getLoggedClient(request).getId());
	}
	
	/**
	 * This method receives a coupon's information to update from the client
	 * Updates the coupon in the system by the coupon's new information and by the client's id
	 * Returns the updated coupon's information back to the client 
	 */	
	@PutMapping(path = "coupons")
	public Coupon updateCoupon(@RequestBody Coupon coupon, HttpServletRequest request) throws ObjectNotFoundException {
		cs.updateCoupon(coupon, getLoggedClient(request).getId());
		return coupon;
	}
	
	/**
	 * This method receives coupon's id from the client 
	 * Getting the coupon's information by the coupon's id and by the client's id 
	 * Returns the coupon's information to the client
	 */	
	@GetMapping(path = "coupons/{couponId}")
	public Coupon getCoupon(@PathVariable int couponId, HttpServletRequest request) throws ObjectNotFoundException {
		Coupon coupon = cs.getCompanyCoupon(couponId, getLoggedClient(request).getId());
		return coupon;
	}
	
	/**
	 * This method returns all coupons by the client's id
	 */	
	@GetMapping(path = "coupons")
	public List<Coupon> getAllCoupons(HttpServletRequest request) throws ObjectNotFoundException {
		List<Coupon> coupons = cs.getCompanyCoupons(getLoggedClient(request).getId());
		return coupons;
	}

	/**
	 * This method receives a coupon's type from the client
	 * and method returns all purchased coupons by the coupon's type and by the client's id
	 */	
	@GetMapping(path = "coupons/byType/{type}")
	public List<Coupon> getCouponsByType(@PathVariable String type, HttpServletRequest request) throws ObjectNotFoundException {
		List<Coupon> coupons = cs.getCompanyCouponsByType(CouponType.valueOf(type.toUpperCase()), getLoggedClient(request).getId());
		return coupons;
	}

	/**
	 * This method receives a coupon's price from the client
	 * and returns all purchased coupons by the coupon's price and by the client's id
	 */
	@GetMapping(path = "coupons/byPrice/{price}")
	public List<Coupon> getCouponsByDate(@PathVariable double price, HttpServletRequest request) throws ObjectNotFoundException {
		List<Coupon> coupons = cs.getCompanyCouponsByPrice(price, getLoggedClient(request).getId());
		return coupons;	 
	}

	/**
	 * This method receives a coupon's end date from the client
	 * and returns all purchased coupons by the coupon's end date and by the client's id
	 */	
	@GetMapping(path = "coupons/byDate/{endDate}")
	public List<Coupon> getCouponsByDate(@PathVariable Date endDate, HttpServletRequest request) throws CouponSystemException {
		List<Coupon> coupons = cs.getCompanyCouponsByDate(endDate, getLoggedClient(request).getId());
		return coupons;		
	}

}
