package com.practice.shopv3api.exceptions;

import com.practice.shopv3api.dtos.ResponseError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(value = { ShopApiException.class })
    public ResponseEntity<ResponseError> handleShopApiException(ShopApiException e) {
        ResponseError res = new ResponseError(e.getMessage(), e.getCode().value());

        return new ResponseEntity<ResponseError>(res, e.getCode());
    }
}
