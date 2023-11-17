package com.example.demo.exception;

import com.example.demo.dto.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimestamp(LocalDate.now());
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setStatus(HttpStatus.NOT_FOUND);
        errorDetails.setPath(webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDetails> handleApiException(ApiException ex, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimestamp(LocalDate.now());
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST);
        errorDetails.setPath(webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimestamp(LocalDate.now());
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST);
        errorDetails.setPath(webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> handleAccessDeniedException(AccessDeniedException ex, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimestamp(LocalDate.now());
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setStatus(HttpStatus.BAD_REQUEST);
        errorDetails.setPath(webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errors = new HashMap<>();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        allErrors.forEach(error -> {
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName,message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }



}
