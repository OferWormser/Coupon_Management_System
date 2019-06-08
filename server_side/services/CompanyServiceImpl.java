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
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import spring.jpa.CouponSystem.entities.Company;
import spring.jpa.CouponSystem.entities.Coupon;
import spring.jpa.CouponSystem.entities.CouponType;
import spring.jpa.CouponSystem.exceptions.CouponSystemException;
import spring.jpa.CouponSystem.exceptions.LoginException;
import spring.jpa.CouponSystem.exceptions.ObjectAlreadyExistsException;
import spring.jpa.CouponSystem.exceptions.ObjectNotFoundException;
import spring.jpa.CouponSystem.repositories.CompanyRepo;
import spring.jpa.CouponSystem.repositories.CouponRepo;

@Service
@Validated
public class CompanyServiceImpl implements CompanyService {

	//ATTRIBUTES
	@Autowired
	private CompanyRepo companyRepo;
	@Autowired
	private CouponRepo couponRepo;
	
	//METHODS 	
		
	/**
	 * This method creates a coupon and adds it to the company in the system
	 * @param The coupon that a company want to create
	 */
	@Override
	@Transactional
	public void createCoupon(@Valid Coupon coupon, @Positive int companyId) throws CouponSystemException {
		if (coupon.getStartDate().before(new Date(System.currentTimeMillis())) ||
				coupon.getEndDate().before(new Date(System.currentTimeMillis()))) {
			throw new CouponSystemException("Coupon date expired!", coupon.getTitle());
		}
		Coupon check = couponRepo.findCouponByTitle(coupon.getTitle());
		if (check != null)
			throw new ObjectAlreadyExistsException("Coupon already exists!", coupon.getTitle());		
		Company company = companyRepo.findById(companyId).get();
		coupon.setCompany(company);
		couponRepo.save(coupon);
	}

	/**
	 * This method deletes a coupon from all tables in the system 
	 * @param The coupon to remove
	 */
	@Override
	@Transactional
	public void removeCoupon(@Positive int couponId, @Positive int companyId) throws ObjectNotFoundException {
        Optional<Coupon> optionalCoupon = couponRepo.findById(couponId);
        if ( !optionalCoupon.isPresent() )
			throw new ObjectNotFoundException("Coupon with id: " + couponId + " not found!", "removeCoupon()");
		couponRepo.removeCoupon(couponId, companyId);
	}

	/**
	 * This method updates a coupons new end date and price in the system
	 * @param The coupon to update
	 * @param The new coupons end date to update
	 * @param The new coupons price to update 
	 */
	@Override
	@Transactional
	public void updateCoupon(@Valid Coupon coupon, @Positive int companyId) throws ObjectNotFoundException {
		Coupon check = couponRepo.findCouponByTitle(coupon.getTitle());
		if (check == null) 
			throw new ObjectNotFoundException("Coupon: " + coupon.getTitle() + " not found!", "updateCoupon()");
		couponRepo.updateCoupon(coupon.getEndDate(), coupon.getPrice(), check.getId(), companyId);
	}

	/**
	 * This method returns a coupon information from the system by id
	 * @param The wanted coupons id to get information 
	 */
	@Override
	public Coupon getCompanyCoupon(@Positive int couponId, @Positive int companyId) throws ObjectNotFoundException{
		Coupon coupon = companyRepo.getCompanyCoupon(couponId, companyId);
		if (coupon == null) 
			throw new ObjectNotFoundException("Coupon with id: " + couponId + " not found!", "getCompanyCoupon()");
		return coupon;
	}

	/**
	 * This method returns a company coupons from the system to array list 
	 * @param The company id to get coupons
	 */
	@Override
	public List<Coupon> getCompanyCoupons(@Positive int companyId) throws ObjectNotFoundException {
		ArrayList<Coupon> coupons = (ArrayList<Coupon>) companyRepo.getCompanyCoupons(companyId);
		if (coupons == null)
			throw new ObjectNotFoundException("Coupons not found!", "getCompanyCoupons()");
		return coupons;
	}

	/**
	 * This method returns a company coupons by type from the system to array list
	 * @param The coupon type as a filter
	 */
	@Override
	public List<Coupon> getCompanyCouponsByType(@NotNull CouponType couponType, @Positive int companyId) throws ObjectNotFoundException {
		ArrayList<Coupon> coupons = (ArrayList<Coupon>) 
				companyRepo.getCompanyCouponsByType(companyId, couponType);
		if (coupons == null)
			throw new ObjectNotFoundException("Coupons not found!", "getCompanyCouponsByType()");
		return coupons;	
	}

	/**
	 * This method returns a company coupons by maximum price from the system to array list 
	 * @param The coupon price as a filter
	 */
	@Override
	public List<Coupon> getCompanyCouponsByPrice(@Positive double couponPrice, @Positive int companyId) throws ObjectNotFoundException {
		ArrayList<Coupon> coupons = (ArrayList<Coupon>) 
				companyRepo.getCompanyCouponsByPrice(companyId, couponPrice);
		if (coupons == null)
			throw new ObjectNotFoundException("Coupons not found!", "getCompanyCouponsByPrice()");
		return coupons;	
	}

	/**
	 * This method returns a company coupons by coupons end date from the system to array list 
	 * @param The coupon price as a filter
	 */
	@Override
	public List<Coupon> getCompanyCouponsByDate(@NotNull Date couponEndDate, @Positive int companyId) throws CouponSystemException {
		if (couponEndDate.before(new Date(System.currentTimeMillis()))) {
			throw new CouponSystemException("The date has expired!", "getCompanyCouponsByDate()");			
		}
		ArrayList<Coupon> coupons = (ArrayList<Coupon>) 
				companyRepo.getCompanyCouponsByDate(companyId, couponEndDate);
		if (coupons == null)
			throw new ObjectNotFoundException("Coupons not found!", "getCompanyCouponsByDate()");
		return coupons;	
	}
	
	/**
	 * This method logs in a company client by name,password and client type and returns the company information
	 * Throws an exception if the company's login details are wrong 
	 * @param The company's user name
	 * @param The company's password
	 * @param The company's client type
	 */	
	@Override
	public Company login(@NotNull @NotBlank String name, @NotNull @NotBlank String password,
			@NotNull ClientType type) throws LoginException {
		if (!type.equals(ClientType.COMPANY))
			throw new LoginException("Login failed!", "login()");
		Company loggedIn =companyRepo.findCompanyByCompNameAndPassword(name, password);
		if (loggedIn == null)
			throw new LoginException("Login failed!", "login()");
		return loggedIn;
	}

}
