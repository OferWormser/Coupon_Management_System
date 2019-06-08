package spring.jpa.CouponSystem.repositories;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.jpa.CouponSystem.entities.Coupon;
import spring.jpa.CouponSystem.entities.CouponType;
import spring.jpa.CouponSystem.entities.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	@Transactional
	@Modifying
	@Query("UPDATE Customer c SET c.password = :password WHERE c.id = :customerId")
	void updateCustomer(@Param("password") String password ,@Param("customerId")  int customerId);
	
	@Query("SELECT coup FROM Coupon coup WHERE coup.id = :couponId AND coup.id IN "
			+ "(SELECT coup.id FROM coup.customers cust WHERE cust.id = :customerId)")
	Coupon getCustomerCoupon(@Param("couponId") int couponId, @Param("customerId") int customerId);
	
	@Query("SELECT coup FROM Coupon coup WHERE coup.id IN"
			+ " (SELECT coup.id FROM coup.customers cust WHERE cust.id = :customerId)")
	ArrayList<Coupon> getCustomerCoupons(@Param("customerId") int customerId);

	@Query("SELECT coup FROM Coupon coup WHERE coup.type = :type AND coup.id IN "
			+ "(SELECT coup.id FROM coup.customers cust WHERE cust.id = :customerId)")
	ArrayList<Coupon> getCustomerCouponsByType(@Param("type")CouponType type ,@Param("customerId") int customerId);

	@Query("SELECT coup FROM Coupon coup WHERE coup.price <= :price AND coup.id IN "
			+ "(SELECT coup.id FROM coup.customers cust WHERE cust.id = :customerId)")
	ArrayList<Coupon> getCustomerCouponsByPrice(@Param("price")double price ,@Param("customerId") int customerId);

	@Query("SELECT coup FROM Coupon coup WHERE coup.endDate <= :endDate AND coup.id IN "
			+ "(SELECT coup.id FROM coup.customers cust WHERE cust.id = :customerId)")
	ArrayList<Coupon> getCustomerCouponsByDate(@Param("endDate") Date endDate ,@Param("customerId") int customerId);

	Customer findCustomerByCustName(String customerName);
	
	Customer findCustomerByCustNameAndPassword (String customerName, String password);

}
