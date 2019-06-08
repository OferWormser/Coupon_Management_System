package spring.jpa.CouponSystem;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import spring.jpa.CouponSystem.entities.Company;
import spring.jpa.CouponSystem.entities.Coupon;
import spring.jpa.CouponSystem.entities.CouponType;
import spring.jpa.CouponSystem.entities.Customer;
import spring.jpa.CouponSystem.exceptions.CouponSystemException;
import spring.jpa.CouponSystem.services.AdminServiceImpl;
import spring.jpa.CouponSystem.services.ClientType;
import spring.jpa.CouponSystem.services.CompanyServiceImpl;
import spring.jpa.CouponSystem.services.CustomerServiceImpl;

public class Test {

	/*
	AdminServiceImpl as = new AdminServiceImpl();
	CompanyServiceImpl cos = new CompanyServiceImpl();
	CustomerServiceImpl cus = new CustomerServiceImpl();

	ConfigurableApplicationContext context =
	SpringApplication.run(CouponSystemApplication.class, args);


	try {
		as.login("admin", "1234", ClientType.ADMIN);	
		as.createCompany(new Company("Ofer", "123456", "ofer.wormser@gmail.com"));
		as.createCompany(new Company("Tzvika", "123456", "tzvika.lorber@gmail.com"));		
		as.createCompany(new Company("Malki", "123456", "malki@gmail.com"));
		ArrayList<Company> companies = (ArrayList<Company>) as.getAllCompanies();
		System.out.println(companies);
		Company co1 = as.getCompany(1);
		System.out.println(co1);
		as.removeCompany(22);		
		as.updateCompany(new Company(2, null, "1234", "skunky2.gmail.com"));
		as.createCustomer(new Customer("Ofer", "123456"));
		as.createCustomer(new Customer("Tzvika", "123456"));
		as.createCustomer(new Customer("Malki", "123456"));
		ArrayList<Customer> customers = new ArrayList<Customer>();
		customers = (ArrayList<Customer>) as.getAllCustomers();
		System.out.println(customers);
		Customer cu1 = as.getCustomer(4);
		System.out.println(cu1);
		as.removeCustomer(6);
		as.updateCustomer(new Customer(5, null, "1234"));
		
		cos.login("Malki", "123456", ClientType.COMPANY);
		Coupon c1 = new Coupon("Tofu", Date.valueOf("2019-03-01"), Date.valueOf("2019-06-30") , 5, CouponType.FOOD, "blah", 10, "gfd");
		cos.createCoupon(c1, 1);
		Coupon c2 = new Coupon("Beatles", Date.valueOf("2019-03-01"), Date.valueOf("2019-06-30"), 5, CouponType.MUSIC, "blah", 10, "gfd");
		cos.createCoupon(c2, 2);		
		Coupon c3 = new Coupon("Beck", Date.valueOf("2019-03-01"), Date.valueOf("2019-06-30"), 5, CouponType.MUSIC, "blah", 20, "gfd");
		cos.createCoupon(c3, 1);		
		System.out.println(cos.getCompanyCoupon(c1.getId(),2));
		cos.updateCoupon(new Coupon(null, null, Date.valueOf("2019-07-30"), 5, null, null, 20, null), c3.getId());
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();
		coupons = (ArrayList<Coupon>) cos.getCompanyCoupons(1);
		System.out.println(coupons);
		coupons = (ArrayList<Coupon>) cos.getCompanyCouponsByType(CouponType.FOOD, 1);
		System.out.println(coupons);
		coupons = (ArrayList<Coupon>) cos.getCompanyCouponsByPrice(20, 1);
		System.out.println(coupons);
		coupons = (ArrayList<Coupon>) cos.getCompanyCouponsByDate(Date.valueOf("2019-06-30"), 1);
		System.out.println(coupons);
		Coupon c4 = cos.getCompanyCoupon(5, 1);
		cos.removeCoupon(5, 1);
		
		cus.login("Malki", "123456", ClientType.CUSTOMER);
		cus.purchaseCoupon(5, 1);
		cus.purchaseCoupon(4,1);
		cus.getPurchasedCoupon(4, 1);
		ArrayList<Coupon> coupons2 = new ArrayList<Coupon>();
		coupons2 = (ArrayList<Coupon>) cus.getAllPurchasedCoupons(1);
		System.out.println(coupons2);
		coupons2 = (ArrayList<Coupon>) cus.getPurchasedCouponsByType(CouponType.FOOD, 1);
		System.out.println(coupons2);
		coupons2 = (ArrayList<Coupon>) cus.getPurchasedCouponsByPrice(20, 1);
		System.out.println(coupons2);
		coupons2 = (ArrayList<Coupon>) cus.getPurchasedCouponsByDate(Date.valueOf("2019-06-30"), 1);
		System.out.println(coupons2);
		} catch (CouponSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	*/
}
