package com.gl.springbootcrudjpaoracledemo.exception;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.gl.springbootcrudjpaoracledemo.constant.SiteConstant;
import com.gl.springbootcrudjpaoracledemo.handler.ServerResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

    
    /**
     *
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        log.debug("Inside Global Exception handler And Exception is "+ex.getMessage() );
        ErrorDetails errorDetails = new ErrorDetails(new Date(), SiteConstant.FAILED,
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UserServerException.class)
    public ResponseEntity<Object> commonException(UserServerException exception) {

        return ServerResponseHandler.errorResponse(exception.getErrorCode(), exception.getErrorMessage(), exception.getHttpStatus());
    }



    @ExceptionHandler
    public ResponseEntity<Object> handleException(InvalidData exc) {
        return ServerResponseHandler.failureResponse(SiteConstant.FORBIDDEN_ERROR, exc.getMessage(), null,HttpStatus.NOT_FOUND);
    }

    
    //exception handler for already existing data
    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<Object> alreadyExists(AlreadyExistsException ex) {
    	LOGGER.error(ex.getMessage());
        return ServerResponseHandler.errorResponse(ex.errorCode, ex.getMessage(), HttpStatus.CONFLICT);
    }

    //exception handler for entities not found
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundException(NotFoundException ex) {
        LOGGER.error(ex.getMessage());
        return ServerResponseHandler.errorResponse(HttpStatus.NOT_FOUND.value()+"", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //exception handler for Bad Request
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> badRequestException(BadRequestException ex) {
        LOGGER.error(ex.getMessage());
        return ServerResponseHandler.errorResponse(HttpStatus.BAD_REQUEST.value()+"", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exc,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Set<FieldError> validationFailedField = new HashSet<>();
        for (final org.springframework.validation.FieldError error : exc.getBindingResult().getFieldErrors()) {
            FieldError fieldError = new FieldError(error.getField(), error.getDefaultMessage());
            validationFailedField.add(fieldError);
        }
        return ServerResponseHandler.failureResponse(SiteConstant.INVALID_REQUEST, "Validation Failed",
                validationFailedField, HttpStatus.BAD_REQUEST);
    }

    //Exception handler for missing parameter in request.
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        LOGGER.error(ex.getMessage());
        return ServerResponseHandler.failureResponse(SiteConstant.INVALID_REQUEST, ex.getMessage(),
                ex.getParameterName(), HttpStatus.BAD_REQUEST);
    }

    //Exception handler for missing header in request.
    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ResponseEntity<Object> handleMissingHeader(
            MissingRequestHeaderException ex) {
        LOGGER.error(ex.getMessage());
        return ServerResponseHandler.failureResponse(SiteConstant.INVALID_REQUEST, ex.getMessage(),
                null, HttpStatus.BAD_REQUEST);
    }
    
  //exception handler for directry not creating
    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<Object> notCreateException(FileStorageException ex) {
        LOGGER.error(ex.getMessage());
        return ServerResponseHandler.errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value()+"", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    //exception handler for File Not Found
    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Object> fileNotFoundException(FileNotFoundException ex) {
        LOGGER.error(ex.getMessage());
        return ServerResponseHandler.errorResponse(HttpStatus.NOT_FOUND.value()+"", ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    

    
    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<Object> handleJsonError(MismatchedInputException ex){
    	 LOGGER.error("############ handleJsonError :: "+ex.getMessage());
    	 return ServerResponseHandler.errorResponse(HttpStatus.BAD_REQUEST.value()+"", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
