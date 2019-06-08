package spring.jpa.CouponSystem.repositories;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.jpa.CouponSystem.entities.Company;
import spring.jpa.CouponSystem.entities.Coupon;
import spring.jpa.CouponSystem.entities.CouponType;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {

	@Transactional
	@Modifying
	@Query("UPDATE Company c SET c.password = :password , c.email = :email WHERE c.id = :id")
	void updateCompany(@Param("password") String newPassword, @Param("email") String newEmail, @Param("id") int id);

	@Transactional
	@Modifying
	@Query("DELETE FROM Coupon c WHERE c.company.id = :companyId")
	void removeAllCompanyCoupons(@Param("companyId") int companyId);
	
	@Query("SELECT c FROM Coupon c WHERE c.id = :couponId AND c.company.id = :companyId ")
	Coupon getCompanyCoupon(@Param("couponId") int couponId, @Param("companyId") int companyId);
	
	@Query("SELECT c FROM Coupon c WHERE c.company.id = :companyId ")
	ArrayList<Coupon> getCompanyCoupons(@Param("companyId") int companyId);
	
	@Query("SELECT c FROM Coupon c WHERE c.company.id = :companyId AND c.type = :type")
	ArrayList<Coupon> getCompanyCouponsByType(@Param("companyId") int companyId , @Param("type") CouponType type);
	
	@Query("SELECT c FROM Coupon c WHERE c.company.id = :companyId AND c.price <= :price")
	ArrayList<Coupon> getCompanyCouponsByPrice(@Param("companyId") int companyId, @Param("price") double price);

	@Query("SELECT c FROM Coupon c WHERE c.company.id = :companyId AND c.endDate <= :couponEndDate")
	ArrayList<Coupon> getCompanyCouponsByDate(@Param("companyId") int companyId, @Param("couponEndDate") Date couponEndDate);

	Company findCompanyByCompName(String companyName);
	
	Company findCompanyByCompNameAndPassword(String companyName , String password);

}
