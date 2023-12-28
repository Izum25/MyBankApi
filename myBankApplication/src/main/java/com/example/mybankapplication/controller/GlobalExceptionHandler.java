package com.example.mybankapplication.controller;

import com.example.mybankapplication.error.ErrorDetails;
import com.example.mybankapplication.error.ValidationError;
import com.example.mybankapplication.exception.DataAlreadyExistsException;
import com.example.mybankapplication.exception.NotDataFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

//    public ResponseEntity<ErrorDetails> handleJpaSystemException(
//            JpaSystemException ex, WebRequest webRequest){
//        return new ResponseEntity<>(createErrorDetails(ex, webRequest, HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleCustomerNotFoundException(
            NotDataFoundException ex, WebRequest webRequest) {
        return new ResponseEntity<>(createErrorDetails(ex, webRequest, HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleCustomerAlreadyExistsException(
            DataAlreadyExistsException ex, WebRequest webRequest) {
        return new ResponseEntity<>(createErrorDetails(ex, webRequest, HttpStatus.CONFLICT), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    private ErrorDetails createErrorDetails(
            Exception ex, WebRequest webRequest, HttpStatus status) {
        log.error("{} exception at path {}", ex.getClass().getSimpleName(), webRequest.getDescription(false), ex);

        List<ValidationError> validationErrors = new ArrayList<>();
        validationErrors.add(new ValidationError(ex.getMessage(), status.getReasonPhrase()));

        Map<String, List<ValidationError>> errorMap = new HashMap<>();
        errorMap.put("errors", validationErrors);

        return ErrorDetails.builder()
                .timeStamp(new Date())
                .status(status.value())
                .errors(errorMap)
                .detail(ex.getMessage())
                .developerMessage(ex.toString())
                .path(webRequest.getDescription(false))
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            @NonNull HttpHeaders headers,
            HttpStatusCode status,
            @NonNull WebRequest request) {
        ErrorDetails errorDetail = new ErrorDetails();
        errorDetail.setTimeStamp(new Date());
        errorDetail.setStatus(status.value());
        errorDetail.setTitle("Message Not Readable");
        errorDetail.setDetail("The request body is not readable. Please check the request format.");
        errorDetail.setDeveloperMessage(ex.getClass().getName());

        return handleExceptionInternal(ex, errorDetail, headers, status, request);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException mane,
            @NonNull HttpHeaders headers,
            HttpStatusCode status,
            @NonNull WebRequest request) {
        ErrorDetails errorDetail = new ErrorDetails();
        errorDetail.setTimeStamp(new Date());
        errorDetail.setStatus(status.value());
        errorDetail.setTitle("Argument Not Valid");
        errorDetail.setDetail(mane.getMessage());
        errorDetail.setDeveloperMessage(mane.getClass().getName());

        List<FieldError> fieldErrors = mane.getBindingResult().getFieldErrors();
        for (FieldError fe : fieldErrors) {
            List<ValidationError> validationErrorList = errorDetail.getErrors()
                    .computeIfAbsent(fe.getField(), k -> new ArrayList<>());
            ValidationError validationError = new ValidationError();
            validationError.setCode(fe.getCode());
            validationError.setMessage(messageSource.getMessage(fe, null));
            validationErrorList.add(validationError);
        }

        return handleExceptionInternal(mane, errorDetail, headers, status, request);
    }
}
