package com.application.app.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class JwtResponse implements Serializable {
    private final Long id;
    private final String userName;
    private final String bearerToken;
}