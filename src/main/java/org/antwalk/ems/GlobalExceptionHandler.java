package org.antwalk.ems;

import java.util.Date;

import org.antwalk.ems.exception.ErrorDetails;
import org.antwalk.ems.exception.ResourceNotFoundException;
//import org.antwalk.ems.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	/*
	 * @ExceptionHandler(UserNotFoundException.class) public ResponseEntity<?>
	 * userNotFoundException(UserNotFoundException ex, WebRequest request) {
	 * ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	 * request.getDescription(false)); return new ResponseEntity<>(errorDetails,
	 * HttpStatus.NOT_FOUND); }
	 */

	// do exception handling for data adding

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExceptionHandler(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
