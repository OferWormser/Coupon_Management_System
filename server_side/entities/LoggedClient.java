package spring.jpa.CouponSystem.entities;

import lombok.Data;
import spring.jpa.CouponSystem.services.ClientType;

@Data
public class LoggedClient {

	//ATTRIBUTES
	private int id;
	private ClientType clientType;

	//CONSTRUCTOR
	public LoggedClient(int id, ClientType clientType) {
		this.id = id;
		this.clientType = clientType;
	}
	
		
	
	
}
