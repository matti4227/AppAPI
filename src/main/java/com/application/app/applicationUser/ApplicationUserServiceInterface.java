package com.application.app.applicationUser;

import java.util.List;

public interface ApplicationUserServiceInterface {

    ApplicationUser createUser(ApplicationUserRequest user);

    ApplicationUser getUser(Long id);

    ApplicationUserResponse getResponseUser(ApplicationUser user) throws Exception;

    ApplicationUser getUserByName(String username);

    List<ApplicationUser> getUsers();

    ApplicationUser updateUser(ApplicationUserRequest userRequest);

    ApplicationUser updateUserAvatar(Long id, byte[] avatar);
}
