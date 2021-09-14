package br.com.gt.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * Custom Exception Handler
 *
 * @see https://www.baeldung.com/exception-handling-for-rest-with-spring#methodSecurity
 */
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {
	
	/**
	 * Para formatar a mensagem de erro disparada através dos Beans Validations...
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
		MethodArgumentNotValidException ex, 
		HttpHeaders headers, 
		HttpStatus status, 
		WebRequest request
	) {

		Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });

	    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler({
		GenericException.class
	})
	public final ResponseEntity<Object> handleGenericException(GenericException ex, WebRequest request) throws Exception {
		
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		return new ResponseEntity<>(ex.getMessage(), headers, status);
		
	}
	
	@ExceptionHandler({
		NotFoundException.class
	})
	public final ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) throws Exception {
		
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<>(ex.getMessage(), headers, status);
		
	}
	
	@ExceptionHandler({
		UnauthorizedException.class
	})
	public final ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) throws Exception {
		
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		return new ResponseEntity<>(ex.getMessage(), headers, status);
		
	}
	
	
	
}
