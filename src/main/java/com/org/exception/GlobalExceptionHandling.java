package com.org.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {
	@ExceptionHandler(InvalidSeatException.class)
	public ResponseEntity<?> handleInvalidSeatException(InvalidSeatException ex) {
		AppError error = new AppError(ex.getMessage(), new Date());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	/*
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public
	 * ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
	 * MethodArgumentNotValidException ex) { Map<String, String> errors = new
	 * HashMap<>();
	 * 
	 * List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
	 * 
	 * for (FieldError error : fieldErrors) { errors.put(error.getField(),
	 * error.getDefaultMessage()); } return new ResponseEntity<Map<String,
	 * String>>(errors, HttpStatus.BAD_REQUEST);
	 * 
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> fieldError = new HashMap<>();
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		for (FieldError err : fieldErrors) {
			fieldError.put(err.getField(), err.getDefaultMessage());
		}
		return new ResponseEntity<Map<String, String>>(fieldError, HttpStatus.BAD_REQUEST);

	}

}
