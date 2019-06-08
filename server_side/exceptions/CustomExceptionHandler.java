package spring.jpa.CouponSystem.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import spring.jpa.CouponSystem.entities.ApiError;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler( ConstraintViolationException.class )
    public ResponseEntity<Object> handleConstraintViolationException( ConstraintViolationException e) {
         
        List<String> errorMessages = new ArrayList<>();
         
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for ( ConstraintViolation<?> violation : violations ) {
            errorMessages.add("Invalid value " + violation.getInvalidValue()
            + " for property " + violation.getPropertyPath() + " : " + violation.getMessage());
        }
         
        ApiError apiError = new ApiError(errorMessages.size() + " violation errors occurred.",errorMessages);
        return new ResponseEntity<Object>(apiError,HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler( Throwable.class )
    public ResponseEntity<Object> handleThrowable( Throwable t) {
        
        ApiError apiError =
        new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Something wrong happened... Please contact the admin!");
        return new ResponseEntity<Object>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }
     
    @ExceptionHandler( CouponSystemException.class )
    public ResponseEntity<Object> handleCouponSystemException( CouponSystemException e) {
         
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        return new ResponseEntity<Object>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler( LoginException.class )
    public ResponseEntity<Object> handleLoginException( LoginException e) {
         
        ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, e.getMessage());
        return new ResponseEntity<Object>(apiError,HttpStatus.UNAUTHORIZED);  
    }

    @ExceptionHandler( ObjectNotFoundException.class )
    public ResponseEntity<Object> handleObjectNotFoundException( ObjectNotFoundException e) {
         
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<Object>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler( ObjectAlreadyExistsException.class )
    public ResponseEntity<Object> handleObjectAlreadyExistsException( ObjectAlreadyExistsException e) {
         
        ApiError apiError = new ApiError(HttpStatus.FOUND, e.getMessage());
        return new ResponseEntity<Object>(apiError,HttpStatus.FOUND);
    }
    
    
    @ExceptionHandler( NoHandlerFoundException.class )
    public ResponseEntity<Object> handleResourceNotFound( NoHandlerFoundException notFoundException) {
 
         
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,
                "Could not found " + notFoundException.getRequestURL() + " (" + notFoundException.getHttpMethod() + ")");
        return new ResponseEntity<Object>(apiError,HttpStatus.NOT_FOUND);
    }
	
}
