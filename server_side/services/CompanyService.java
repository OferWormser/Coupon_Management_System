package spring.jpa.CouponSystem.services;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import spring.jpa.CouponSystem.entities.Company;
import spring.jpa.CouponSystem.entities.Coupon;
import spring.jpa.CouponSystem.entities.CouponType;
import spring.jpa.CouponSystem.exceptions.CouponSystemException;
import spring.jpa.CouponSystem.exceptions.LoginException;
import spring.jpa.CouponSystem.exceptions.ObjectNotFoundException;


public interface CompanyService {

	//METHODS	
	/**
	 * This method creates a coupon
	 * @param The coupon that a company wants to create
	 * @param the company's id 
	 */
	void createCoupon(@Valid Coupon coupon, @Positive int companyId) throws CouponSystemException;
	
	/**
	 * This method deletes a coupon 
	 * @param The coupon's id to remove
	 * @param The company id that owns the coupon
	 */
	void removeCoupon(@Positive int couponId, @Positive int companyId) throws ObjectNotFoundException;
	
	/**
	 * This method updates a coupon
	 * @param The coupon to update
	 * @param The company id that owns the coupon
	 */
	void updateCoupon(@Valid Coupon coupon, @Positive int companyId) throws ObjectNotFoundException;

	/**
	 * This method returns a company's coupon by id
	 * @param The coupon's id 
	 * @param The company id that owns the coupon
	 */
	Coupon getCompanyCoupon(@Positive int couponId, @Positive int companyId) throws ObjectNotFoundException;
	
	/**
	 * This method returns all company's coupons to array list
	 * @param The company id that owns the coupon
	 */
	List<Coupon> getCompanyCoupons(@Positive int companyId) throws ObjectNotFoundException;
	
	/**
	 * This method returns company's coupons by type to array list
	 * @param The coupon type as a filter
	 * @param The company id that owns the coupon
	 */
	List<Coupon> getCompanyCouponsByType(@NotNull CouponType couponType, @Positive int companyId) throws ObjectNotFoundException;
	
	/**
	 * This method returns company's coupons by maximum price to array list
	 * @param The coupon price as a filter
	 * @param The company id that owns the coupon
	 */
	List<Coupon> getCompanyCouponsByPrice(@Positive double couponPrice, @Positive int companyId) throws ObjectNotFoundException;	
	
	/**
	 * This method returns company's coupons by end date to array list
	 * @param The coupon price as a filter
	 * @param The company id that owns the coupon
	 */
	List<Coupon> getCompanyCouponsByDate(@NotNull Date couponEndDate, @Positive int companyId) throws CouponSystemException;

	/**
	 * This method logs in a company client by name,password and client type and returns the company information
	 * @param The company's user name
	 * @param The company's password
	 * @param The company's client type
	 */	
	Company login(@NotNull @NotBlank String name, @NotNull @NotBlank String password,
			@NotNull  ClientType type) throws LoginException;
}
