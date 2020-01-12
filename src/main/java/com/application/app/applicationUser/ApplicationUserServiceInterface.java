package com.application.app.applicationUser;

import java.util.List;

public interface ApplicationUserServiceInterface {

    ApplicationUser createUser(ApplicationUserRequest user);

    ApplicationUser getUser(Long id);

    ApplicationUser getUserByCookbook(Long id);

    ApplicationUser getUserByFridge(Long id);

    ApplicationUser getUserByRecipe(Long id);

    ApplicationUserResponse getResponseUser(ApplicationUser user) throws Exception;

    ApplicationUser getUserByName(String username);

    List<ApplicationUser> getUsers();

    ApplicationUser updateUser(Long id, ApplicationUserRequest userRequest);

    ApplicationUser updateUserAvatar(Long id, byte[] avatar);
}
