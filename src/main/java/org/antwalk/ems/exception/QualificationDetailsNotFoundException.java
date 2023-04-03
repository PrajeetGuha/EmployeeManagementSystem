package org.antwalk.ems.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class QualificationDetailsNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

	public QualificationDetailsNotFoundException(String message){
    	super(message);
    }
}