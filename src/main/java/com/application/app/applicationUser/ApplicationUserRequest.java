package com.application.app.applicationUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationUserRequest {
    private String email;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
