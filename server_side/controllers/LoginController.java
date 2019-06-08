package spring.jpa.CouponSystem.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.jpa.CouponSystem.entities.Company;
import spring.jpa.CouponSystem.entities.Customer;
import spring.jpa.CouponSystem.entities.LoggedClient;
import spring.jpa.CouponSystem.exceptions.LoginException;
import spring.jpa.CouponSystem.services.AdminServiceImpl;
import spring.jpa.CouponSystem.services.ClientType;
import spring.jpa.CouponSystem.services.CompanyServiceImpl;
import spring.jpa.CouponSystem.services.CustomerServiceImpl;

@RestController
@RequestMapping("login")
public class LoginController {

	//ATTRIBUTES
	@Autowired
	private AdminServiceImpl adminService;
	
	@Autowired
	private CompanyServiceImpl companyService;
	
	@Autowired
	private CustomerServiceImpl customerService;

	//METHODS
	
	/**
	 * This method logs in a client by type : admin / company / customer 
	 * Saves client's details on a session and returns the details back to client
	 */	
	@PostMapping(path="")
	public LoggedClient login(@RequestBody Map<String, Object> body, HttpServletRequest request) throws LoginException{
		ClientType clientType = ClientType.valueOf(body.get("clientType").toString().toUpperCase());
		LoggedClient loggedClient = null;
		switch (clientType) {
		case ADMIN:
			adminService.login(body.get("name").toString(), body.get("password").toString(), clientType);
			loggedClient = new LoggedClient(1234, clientType);
			request.getSession().setAttribute("loggedClient", loggedClient);
			break;
			
		case COMPANY:
			Company company = companyService.login(body.get("name").toString(), body.get("password").toString(), clientType);
			loggedClient = new LoggedClient(company.getId(), clientType);
			request.getSession().setAttribute("loggedClient", loggedClient);
			break;
			
		case CUSTOMER:
			Customer customer = customerService.login(body.get("name").toString(), body.get("password").toString(), clientType);
			loggedClient = new LoggedClient(customer.getId(), clientType);
			request.getSession().setAttribute("loggedClient", loggedClient);
			break;
		}
		return loggedClient;
	}
	
	/**
	 * This method logs out in a client by deleting the session
	 */	
	@GetMapping(path="")
	public void logout(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}
	
}
