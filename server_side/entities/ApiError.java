package spring.jpa.CouponSystem.entities;


import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiError {
	
	private String errorText;
    private HttpStatus status;
    private List<String> messages;
     
     
    public ApiError(HttpStatus status, String... messages) {
        super();
        this.status = status;
        this.messages = Arrays.asList(messages);
    }
     
    public ApiError(String errorText, List<String> messages) {
        super();
        this.errorText = errorText;
        this.messages = messages;
	}

	public void addMessage(String message) {
        messages.add(message);
    }
 
 	
}
