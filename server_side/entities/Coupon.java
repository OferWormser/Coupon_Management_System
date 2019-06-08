package spring.jpa.CouponSystem.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Coupon {

	//ATTRIBUTES
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotBlank
	@Column(nullable = true, unique = true)
	private String title;

	@NotNull
	@Column(nullable = true)
	private Date startDate;

	@NotNull
	@Column(nullable = true)
	private Date endDate;
	
	@PositiveOrZero
	@Column(nullable = true)
	private int amount;
	
	@NotNull
	@Column(nullable = true)
	@Enumerated(EnumType.STRING)
	private CouponType type;
	
	@NotNull
	@NotBlank
	@Column(nullable = true)
	private String message;
	
	@Positive
	@Column(nullable = true)
	private double price;
	
	@NotNull
	@NotBlank
	@Column(nullable = true, columnDefinition = "text")
	private String image;

	@ManyToOne
	@JsonIgnore
	@Valid
	private Company company;
	
	@ManyToMany ( mappedBy = "coupons", fetch = FetchType.LAZY )
	@JsonIgnore
	@Valid
	private Collection<Customer> customers = new ArrayList<Customer>();
	
	
	//CONSTRUCTORS
	public Coupon(int id, String title, Date startDate, Date endDate, int amount, CouponType type, String message,
			double price, String image) {
		this.id = id;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}
	
	public Coupon(String title, Date startDate, Date endDate, int amount, CouponType type, String message, double price,
			String image) {
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.type = type;
		this.message = message;
		this.price = price;
		this.image = image;
	}	
	

	//METHODS	
	/**
	 * This method overrides toString (a coupon)
	 */
	@Override
	public String toString() {
		return "Coupon id: " + id + ", title: " + title + ", message: " + message + ", start date: "
	+ startDate	+ ", end date: " + endDate + ", amount: " + amount + ", price: " + price + ", type: " + type;
	}

	/**
	 * This method adds a customer to customers coupons list
	 * @param the customer to add  
	 */
	public void addCustomer (Customer customer) {
		customers.add(customer);
	}
	
}
