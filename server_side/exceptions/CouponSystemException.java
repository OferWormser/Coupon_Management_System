package spring.jpa.CouponSystem.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CouponSystemException extends Exception {

	private String errorValue;

	public CouponSystemException(String message, String errorValue) {
		super(message);
		this.errorValue = errorValue;
	}
	
	

	
	
}
