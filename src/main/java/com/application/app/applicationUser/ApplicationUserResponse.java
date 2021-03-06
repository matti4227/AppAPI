package com.application.app.applicationUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApplicationUserResponse {
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private byte[] avatar;
}
