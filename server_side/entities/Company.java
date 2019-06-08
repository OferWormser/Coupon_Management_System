package spring.jpa.CouponSystem.entities;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Email;
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
public class Company {

	//ATTRIBUTES
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotBlank
	@Column(nullable = true, unique = true)
	private String compName;
	
	@NotNull
	@NotBlank
	@Column(nullable = true)
	private String password;

	@NotNull
	@Email
	@Column(nullable = true, unique = true)
	private String email;
	
	@OneToMany ( mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JsonIgnore
	@Valid
	private Collection<Coupon> coupons = new ArrayList<Coupon>();

	
	//CONSTRUCTORS
	public Company(int id, String compName, String password, String email) {
		this.id = id;
		this.compName = compName;
		this.password = password;
		this.email = email;
	}
	
	public Company(String compName, String password, String email) {
		this.compName = compName;
		this.password = password;
		this.email = email;
	}	

	//METHODS	
	/**
	 * This method overrides toString (a company)
	 */
	@Override
	public String toString() {
		return "Company id: " + id + ", name: " + compName +", email: " + email +", password: " + password;
	}	
	
	
			
}