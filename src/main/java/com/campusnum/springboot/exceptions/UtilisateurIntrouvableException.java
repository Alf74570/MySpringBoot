package com.campusnum.springboot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UtilisateurIntrouvableException extends RuntimeException {
	
	public UtilisateurIntrouvableException(String s) {
		super(s);
	}
}
