package spring.jpa.CouponSystem.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginException extends CouponSystemException {

	private String errorValue;

	public LoginException(String message, String errorValue) {
		super(message, errorValue);
	}	

}
