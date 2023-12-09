package com.practice.shopv3api.exceptions;

import org.springframework.http.HttpStatusCode;

public class ShopApiException extends RuntimeException {
    private HttpStatusCode code;

    public ShopApiException(String message) {
        super(message);
    }

    public ShopApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShopApiException(String message, Throwable cause, HttpStatusCode code) {
        super(message, cause);
        this.code = code;
    }

    public ShopApiException(String message, HttpStatusCode code) {
        super(message);
        this.code = code;
    }

    public HttpStatusCode getCode() {
        return code;
    }
}
