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

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Integer> {

	@Transactional
	@Modifying 
	@Query("DELETE FROM Coupon c WHERE c.id = :couponId AND c.company.id = :companyId")
	void removeCoupon(@Param("couponId") int couponId, @Param("companyId") int companyId);

	@Transactional
	@Modifying 
	@Query("DELETE FROM Coupon c WHERE c.endDate < :endDate")
	void removeExpiredCoupons(@Param("endDate") Date endDate);

	@Transactional
	@Modifying
	@Query("UPDATE Coupon c SET c.endDate = :endDate, c.price = :price WHERE"
			+ " c.id = :couponId AND c.company.id = :companyId")
	void updateCoupon(@Param("endDate") Date endDate, @Param("price") double price,
			@Param("couponId") int couponId, @Param("companyId") int companyId);

	Coupon findCouponByTitle(String title);
	
	ArrayList<Coupon> findCouponByType(CouponType type);
	
	ArrayList<Coupon> findAll();
	
}
