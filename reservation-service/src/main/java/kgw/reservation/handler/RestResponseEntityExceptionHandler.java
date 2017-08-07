package kgw.reservation.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException;

import kgw.reservation.dto.RestError;
import kgw.reservation.exception.MismatchJpegPngFormatException;


@ControllerAdvice
public class RestResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler {
	@ExceptionHandler(MaxUploadSizeExceededException.class)	
	@ResponseBody
    ResponseEntity<?> maxUploadSizeExceededControllerException(HttpServletRequest request, Throwable ex) {
        RestError restError = 
  			  new RestError(HttpStatus.BAD_REQUEST, "파일 용량이 초과하였습니다.",null);
        	return new ResponseEntity<Object>(restError, new HttpHeaders(), restError.getStatus());
    }
	
    @ExceptionHandler(MismatchJpegPngFormatException.class)
    @ResponseBody
    ResponseEntity<?> mismatchJpegPngControllerException(HttpServletRequest request, Throwable ex) {
        RestError restError = 
  			  new RestError(HttpStatus.BAD_REQUEST, ex.getMessage(),null);
        	return new ResponseEntity<Object>(restError, new HttpHeaders(), restError.getStatus());
    }
    
    
	// MethodArgumentNotValidException - @Valid failed validation
		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid (
		  MethodArgumentNotValidException ex, 
		  HttpHeaders headers, 
		  HttpStatus status, 
		  WebRequest request) {
			Map<String, Object> errors = new HashMap<>();
			for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			    errors.put(error.getField(), error.getDefaultMessage());
			}
			for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			    errors.put(error.getObjectName(), error.getDefaultMessage());
			}
			 
			RestError restError = 
			  new RestError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
			return handleExceptionInternal(ex, restError, headers, restError.getStatus(), request);
		}
		
		// MissingServletRequestParameterException - Request param missing 
		@Override
		protected ResponseEntity<Object> handleMissingServletRequestParameter (
		  MissingServletRequestParameterException ex, HttpHeaders headers, 
		  HttpStatus status, WebRequest request) {
			Map<String, Object> error = new HashMap<>();
			error.put(ex.getParameterName(), "parameter mssing");
			 
			RestError restError = 
			  new RestError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
			return new ResponseEntity<Object>(restError, new HttpHeaders(), restError.getStatus());
		}
		
		// ConstraintViolationException  - constraint violations 
		@ExceptionHandler({ ConstraintViolationException.class })
		public ResponseEntity<Object> handleConstraintViolation (
		  ConstraintViolationException ex, WebRequest request) {
			Map<String, Object> errors = new HashMap<>();
			for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			    errors.put(violation.getRootBeanClass().getName(), 
			    		violation.getPropertyPath() + ": " + violation.getMessage());
			    }
			 
			RestError restError = 
			  new RestError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
			return new ResponseEntity<Object>(restError, new HttpHeaders(), restError.getStatus());
		}
		
		// MethodArgumentTypeMismatchException - argument missmatching
		@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
		public ResponseEntity<Object> handleMethodArgumentTypeMismatch (
		  MethodArgumentTypeMismatchException ex, WebRequest request) {
			Map<String, Object> error = new HashMap<>();
			error.put(ex.getName(), "should be of type " + ex.getRequiredType().getName());
			RestError restError = 
				new RestError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
			return new ResponseEntity<Object>(restError, new HttpHeaders(), restError.getStatus());
		}
		
		// invalid JSON format handling 
		@ExceptionHandler({JsonMappingException.class})
		public ResponseEntity<Object> handleJsonMappingException (
		  JsonMappingException ex, WebRequest request) {
			Map<String, Object> error = new HashMap<>();
			error.put("msg",ex.getMessage());
			RestError restError = 
				new RestError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
			return new ResponseEntity<Object>(restError, new HttpHeaders(), restError.getStatus());
		}
		
		// etc 
		@ExceptionHandler({Exception.class})
		public ResponseEntity<Object> handleJsonMappingException (
		  Exception ex, WebRequest request) {
			Map<String, Object> error = new HashMap<>();
			error.put("url", request.getDescription(false));
			error.put("msg", ex.getLocalizedMessage());
			RestError restError = 
					new RestError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
				return new ResponseEntity<Object>(restError, new HttpHeaders(), restError.getStatus());
		}
}
