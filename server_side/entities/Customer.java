package spring.jpa.CouponSystem.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Customer {

	//ATTRIBUTES
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotBlank
	@Column(nullable = true, unique = true)
	private String custName;
	
	@NotNull
	@NotBlank
	@Column(nullable = true)
	private String password;
	
	@ManyToMany (fetch = FetchType.LAZY)
	@JsonIgnore
	@Valid
	private Collection<Coupon> coupons = new ArrayList<Coupon>();

	
	//CONSTRUCTORS
	public Customer(int id, String custName, String password) {
		this.id = id;
		this.custName = custName;
		this.password = password;
	}

	public Customer(String custName, String password) {
		this.custName = custName;
		this.password = password;
	}

	//METHODS	
	/**
	 * This method overrides toString (a customer)
	 */
	@Override
	public String toString() {
		return "Customer id: " + id + ", name: " + custName + ", password: " + password;
	}

	/**
	 * This method adds a coupon to the customers coupons list
	 * @param the coupon to add
	 */
	public void addCoupon (Coupon coupon) {
		coupons.add(coupon);
	}
	
	
	
	
}
