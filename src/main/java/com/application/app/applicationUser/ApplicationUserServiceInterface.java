package com.application.app.applicationUser;

import java.util.List;

public interface ApplicationUserServiceInterface {

    ApplicationUser createUser(ApplicationUserRequest user);

    ApplicationUser getUser(Long id);

    List<ApplicationUser> getUsers();

    ApplicationUser updateUser(ApplicationUserRequest userRequest, Long id);
}
