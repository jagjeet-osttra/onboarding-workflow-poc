package com.osttra.crds.advices;

import com.osttra.crds.exceptions.ProcessNotFoundException;
import com.osttra.crds.exceptions.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProcessNotFoundException.class)
    public ResponseEntity<ApiError> handleProcessNotFoundException(ProcessNotFoundException ex)
    {
        ApiError apiError = ApiError.builder().httpStatus(HttpStatus.NOT_FOUND).error(ex.getLocalizedMessage())
                .build();
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ApiError> handleTaskNotFoundException(TaskNotFoundException ex)
    {
        ApiError apiError = ApiError.builder().httpStatus(HttpStatus.NOT_FOUND).error(ex.getLocalizedMessage())
                .build();
        return new ResponseEntity<>(apiError,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException runtimeException)
    {
        ApiError apiError = ApiError.builder().httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).error(runtimeException.getLocalizedMessage())
                .build();
        return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
