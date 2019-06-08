package spring.jpa.CouponSystem.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjectAlreadyExistsException extends CouponSystemException {

	private String errorValue;

	public ObjectAlreadyExistsException(String message, String errorValue) {
		super(message, errorValue);
	}
	
	
}
