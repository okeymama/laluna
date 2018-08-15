package com.universe.laluna.exception;


import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.info("Inside handleMethodArgumentNotValid method in ExceptionHandler");
		log.error("Exception :", ex);
		
		List<String> errors = new ArrayList<>();

		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}

		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
		log.info("End of handleMethodArgumentNotValid method in ExceptionHandler");
		return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		log.info("Inside handleBindException method in ExceptionHandler");
		log.error("Exception :", ex);
		
		List<String> errors = new ArrayList<>();

		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}

		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}

		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(),
				errors);

		log.info("End of handleBindException method in ExceptionHandler");

		return handleExceptionInternal(ex, exceptionResponse, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.info("Inside handleTypeMismatch method in ExceptionHandler");
		log.error("Exception :", ex);
		
		List<String> error = new ArrayList<>();
		error.add(ex.getValue() + " value for " + ex.getPropertyName() + " should be of type "
				+ ex.getRequiredType());

		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);

		log.info("End of handleTypeMismatch method in ExceptionHandler");

		return handleExceptionInternal(ex, exceptionResponse, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.info("Inside handleMissingServletRequestPart method in ExceptionHandler");

		List<String> error = new ArrayList<>();
		error.add(ex.getRequestPartName() + " part is missing");
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);

		log.info("End of handleMissingServletRequestPart method in ExceptionHandler");

		return handleExceptionInternal(ex, exceptionResponse, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.info("Inside handleMissingServletRequestParameter method in ExceptionHandler");
		
		
		List<String> error = new ArrayList<>();
		error.add(ex.getParameterName() + " parameter is missing");

		ExceptionResponse apiError = new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);

		log.info("End of handleMissingServletRequestParameter method in ExceptionHandler");

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		log.info("Inside handleNoHandlerFoundException method in ExceptionHandler");
		log.error("Exception :", ex);
		
		List<String> error = new ArrayList<>();
		error.add("No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL());

		ExceptionResponse apiError = new ExceptionResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);

		log.info("End of handleNoHandlerFoundException method in ExceptionHandler");

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ RuntimeException.class })
	@ResponseBody
	public ResponseEntity<Object> handleAll(Exception ex) {
		log.info("Inside handleAll method in ExceptionHandler");
		log.error("Exception :", ex);
		List<String> error = new ArrayList<>();
		error.add("Error processing the request on the run time");
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST,
				ex.getLocalizedMessage(), error);
		
		log.info("End of handleAll method in ExceptionHandler");

		return new ResponseEntity<Object>(exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MultipartException.class)
	@ResponseBody
	public ResponseEntity<Object> handleFileException(Exception ex, HttpHeaders headers, WebRequest request) {
		log.info("Inside handleFileException method in ExceptionHandler");
		log.error("Exception :", ex);
		List<String> error = new ArrayList<>();
		error.add("Error loading additional information");
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		
		log.info("End of handleFileException method in ExceptionHandler");
		return handleExceptionInternal(ex, exceptionResponse, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		log.info("Inside handleMethodArgumentTypeMismatch method in ExceptionHandler");
		log.error("Exception :", ex);
		
		List<String> error = new ArrayList<>();
		error.add(ex.getName() + " should be of type " + ex.getRequiredType().getName());
		ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(),
				error);

		log.info("End of handleMethodArgumentTypeMismatch method in ExceptionHandler");
		return new ResponseEntity<Object>(exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		log.info("Inside handleConstraintViolation method in ExceptionHandler");
		log.error("Exception :", ex);
		
		List<String> errors = new ArrayList<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}
		ExceptionResponse apiError = new ExceptionResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);

		log.info("End of handleConstraintViolation method in ExceptionHandler");
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
