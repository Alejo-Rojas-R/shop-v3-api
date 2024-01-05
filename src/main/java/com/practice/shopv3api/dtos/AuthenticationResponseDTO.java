package com.practice.shopv3api.dtos;

public class AuthenticationResponseDTO {

    private String token;

    public AuthenticationResponseDTO(String token) {
        this.token = token;
    }

    public AuthenticationResponseDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}