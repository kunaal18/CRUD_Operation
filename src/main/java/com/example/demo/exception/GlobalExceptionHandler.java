package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.Response;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = LeagueNotFoundException.class)
	public ResponseEntity<Response> leagueNotFoundException(LeagueNotFoundException exception) {
		return new ResponseEntity<>(new Response(true, null, exception.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = PlayerNotFoundException.class)
	public ResponseEntity<Response> playerNotFpundException(PlayerNotFoundException exception) {
		return new ResponseEntity<>(new Response(true, null, exception.getMessage()), HttpStatus.BAD_REQUEST);
	}

}
