package com.application.app.applicationUser;

import java.util.List;

public interface ApplicationUserServiceInterface {

    ApplicationUser createUser(ApplicationUserRequest user);

    ApplicationUser getUserById(Long id);

    ApplicationUser getUser();

    ApplicationUser getUserByCookbook(Long id);

    ApplicationUser getUserByFridge(Long id);

    ApplicationUser getUserByRecipe(Long id);

    ApplicationUserResponse getResponseUser(ApplicationUser user) throws Exception;

    ApplicationUser getUserByName(String username);

    List<ApplicationUser> getUsers();

    ApplicationUser updateUser(ApplicationUserRequest userRequest);

    ApplicationUser updateUserAvatar(byte[] avatar);
}
