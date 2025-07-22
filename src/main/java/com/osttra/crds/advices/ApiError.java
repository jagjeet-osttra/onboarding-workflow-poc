package com.osttra.crds.advices;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private HttpStatus httpStatus;
    private String error;
}
