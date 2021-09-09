package io.coursex.springbootstarter.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.coursex.springbootstarter.model.ErrorMessage;

@ControllerAdvice
public class AppException extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> handleAllException(Exception e, WebRequest req){
		String errorMessageDescription = e.getLocalizedMessage();
		if(errorMessageDescription == null) {
			errorMessageDescription = e.toString();
		}
		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	@ExceptionHandler(value= {NullPointerException.class})
	public ResponseEntity<Object> handleNullPointerException(NullPointerException npe, WebRequest req){
		String errorMessageDescription = npe.getLocalizedMessage();
		if(errorMessageDescription == null) {
			errorMessageDescription = npe.toString().concat(" This is from NullPointerException method handler");
		}
		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	@ExceptionHandler(value= {UserCustomException.class})
	public ResponseEntity<Object> handleUserCustomException(UserCustomException uce, WebRequest req){
		String errorMessageDescription = uce.getLocalizedMessage();
		if(errorMessageDescription == null) {
			errorMessageDescription = uce.toString();
		}
		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
		return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
}
