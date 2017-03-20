package com.reminder.exception;

import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<?> handleException(Exception ex) {
        return prepareResponse(ex.getMessage(), ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity<?> handleConflicts(RuntimeException ex) {
        return prepareResponse(ex.getMessage(), ex, new HttpHeaders(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { AccessDeniedException.class })
    public ResponseEntity<?> handleAccessDenied(Exception ex) {
        return prepareResponse("Access denied message here", ex, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<?> handleBadRequest(MethodArgumentNotValidException ex) {
        return prepareResponse("Bad request", ex, new HttpHeaders(), HttpStatus.BAD_REQUEST, prepareErrorMap(ex.getBindingResult().getFieldErrors()));
    }

    private Map<String, String> prepareErrorMap(List<FieldError> errors) {
        return CollectionUtils.isEmpty(errors) ? Maps.newHashMap() : errors.stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }

    private ResponseEntity<?> prepareResponse(String logMessage, Exception ex, HttpHeaders headers, HttpStatus status) {
        return prepareResponse(logMessage, ex, headers, status, Maps.newHashMap());
    }

    private ResponseEntity<?> prepareResponse(String logMessage, Exception ex, HttpHeaders headers, HttpStatus status, Map<String, String> errorMap) {
        log.info(logMessage);
        log.debug("Error while execute {}", ex);
        return new ResponseEntity<>(new ExceptionMessageResult(logMessage, status, errorMap, status.value()), headers, status);
    }

}
