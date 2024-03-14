package com.aws.epl.demo.exception;

import java.sql.Timestamp;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.aws.epl.demo.core.LocaleService;
import com.aws.epl.demo.utils.Language;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@AllArgsConstructor
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

		 private final LocaleService localeService;
		 
		 
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ErrorResult> handleStudentNotFoundException(StudentNotFoundException ex,
			WebRequest request) {
		String error = ex.getMessage();
		return buildResponseEntity(request, HttpStatus.BAD_REQUEST, "Error_1000", error);
	}
	
	private ResponseEntity<ErrorResult> buildResponseEntity(WebRequest request, HttpStatus status, String type,
			String errorMessage) {
		String path = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
		ErrorResult error = new ErrorResult(status, path,null, errorMessage);
		return new ResponseEntity<ErrorResult>(error, status);
	}

	 

	    /**
	     * Handle TooManyRequestsExceptionException. Triggered when too many
	     * requests are incoming and exceed the limit
	     */
//	    @ExceptionHandler(TooManyRequestsException.class)
//	    public ResponseEntity<ErrorResult> handleTooManyRequestsException(TooManyRequestsException ex,
//	    		WebRequest request) {
//	    	return buildResponseEntity(request, HttpStatus.TOO_MANY_REQUESTS, ex.getMessage());
//	    }
	    
	 /**
	     * Handle MethodArgumentNotValidException. Happens when request body has
	     * validation errors
	     */
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ErrorResult> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	                                                                    WebRequest request) {
	        BindingResult result = ex.getBindingResult();
	        for (ObjectError globalError : result.getGlobalErrors()) {
	            return buildResponseEntity(request, HttpStatus.BAD_REQUEST, globalError.getDefaultMessage());
	        }
	        for (ObjectError fieldError : result.getFieldErrors()) {
	            if (fieldError.getDefaultMessage() != null) {
	                return buildResponseEntity(request, HttpStatus.BAD_REQUEST, fieldError.getDefaultMessage());
	            }
	            return buildResponseEntity(request, HttpStatus.BAD_REQUEST, fieldError.getDefaultMessage());
	        }
	        return buildResponseEntity(request, HttpStatus.BAD_REQUEST, ex.getMessage());

	    }
	    
	    /**
	     * Handle ValidationException. Happens when the request has some validation
	     * errors
	     */
	    private ResponseEntity<ErrorResult> buildResponseEntity(WebRequest request, HttpStatus status,
	                                                            String errorMessage) {
	        String errorTranslated = localeService
	                .getMessage(Language.languageOf(LocaleContextHolder.getLocale().getLanguage()), errorMessage);
	        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
	        ErrorResult error = new ErrorResult(status, path,new Timestamp(System.currentTimeMillis()), errorTranslated);
	        return new ResponseEntity<>(error, status);
	    }
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResult> handle(Exception ex, WebRequest request) {
	    	log.error("Generic exception : {}", ex);
	        return buildResponseEntity(request, HttpStatus.BAD_REQUEST, ex.getMessage());
	    }
	    
	    
	    
	    @ExceptionHandler(ConstraintViolationException.class)
	    public ResponseEntity<ErrorResult> handleConstraintViolationException(ConstraintViolationException ex,
	                                                                          WebRequest request) {
	        String message = ex.getConstraintViolations().stream()
	                .map(p -> p.getConstraintDescriptor().getMessageTemplate()).findFirst().get();
	        return buildResponseEntity(request, HttpStatus.BAD_REQUEST, message);
	    }
	    
	    @ExceptionHandler(MissingServletRequestParameterException.class)
	    public ResponseEntity<?> handleMissingServletRequestParameterException(
	            MissingServletRequestParameterException ex, WebRequest request) {
	        return buildResponseEntity(request, HttpStatus.BAD_REQUEST, ex.getBody().getDetail());
	    }
	    @ExceptionHandler(AccessDeniedException.class)
		public ResponseEntity<ErrorResult> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
			return buildResponseEntity(request, HttpStatus.UNAUTHORIZED, ex.getMessage());
		}
	    @ExceptionHandler(BadCredentialsException.class)
		public ResponseEntity<ErrorResult> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
			return buildResponseEntity(request, HttpStatus.UNAUTHORIZED, ex.getMessage());
		}
	    
	    
}
