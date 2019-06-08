package spring.jpa.CouponSystem.exceptions;

import lombok.Setter;

import lombok.Getter;

@Getter
@Setter
public class ObjectNotFoundException extends CouponSystemException {

	private String errorValue;

	public ObjectNotFoundException(String message, String errorValue) {
		super(message, errorValue);
	}

	
}
