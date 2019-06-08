package spring.jpa.CouponSystem.services;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import spring.jpa.CouponSystem.entities.Coupon;
import spring.jpa.CouponSystem.entities.CouponType;
import spring.jpa.CouponSystem.entities.Customer;
import spring.jpa.CouponSystem.exceptions.CouponSystemException;
import spring.jpa.CouponSystem.exceptions.LoginException;
import spring.jpa.CouponSystem.exceptions.ObjectNotFoundException;


public interface CustomerService {

	//METHODS
	/**
	 * This method adds a coupon to a customer after purchase  
	 * @param The coupon's id that a customer wants to purchase
	 * @param The customer's id
	 */
	void purchaseCoupon(@Positive int couponId, @Positive int customerId) throws CouponSystemException;

	/**
	 * This method returns a customer's coupon by id
	 * @param The customer's id that owns the coupon
	 */
	Coupon getPurchasedCoupon(@Positive int couponId, @Positive int customerId) throws CouponSystemException;
	
	/**
	 * This method returns all customer's coupons to array list
	 * @param The customer's id that owns the coupon
	 */
	List<Coupon> getAllPurchasedCoupons(@Positive int customerId) throws ObjectNotFoundException;
	
	/**
	 * This method returns customer's coupons by type to array list
	 * @param The coupon type as a filter
	 * @param The customer's id that owns the coupon
	 */
	List<Coupon> getPurchasedCouponsByType(@NotNull CouponType couponType, @Positive int customerId) throws ObjectNotFoundException;
	
	/**
	 * This method returns customer's coupons by maximum price to array list
	 * @param The coupon price as a filter
	 * @param The customer's id that owns the coupon
	 */
	List<Coupon> getPurchasedCouponsByPrice(@Positive double couponPrice, @Positive int customerId) throws ObjectNotFoundException;	
	
	/**
	 * This method returns customer's coupons by end date to array list
	 * @param The coupon end date as a filter
	 * @param The customer's id that owns the coupon
	 */
	List<Coupon> getPurchasedCouponsByDate(@NotNull  Date couponEndDate, @Positive int customerId) throws CouponSystemException;

	/**
	 * This method returns all existing coupons in the system to array list
	 */	
	List<Coupon> getAllCoupons() throws ObjectNotFoundException;

	/**
	 * This method logs in a customer client by name,password and client type and returns the customer's information
	 * @param The customer's user name
	 * @param The customer's password
	 * @param The customer's client type
	 */	
	Customer login(@NotNull @NotBlank String name, @NotNull @NotBlank String password, @NotNull ClientType type) throws LoginException;
	
}
