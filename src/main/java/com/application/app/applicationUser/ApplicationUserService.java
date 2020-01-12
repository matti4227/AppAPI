package com.application.app.applicationUser;

import com.application.app.recipe.Recipe;
import com.application.app.recipe.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationUserService implements ApplicationUserServiceInterface {

    @Autowired
    private ApplicationUserRepositoryInterface userRepositoryInterface;

    @Autowired
    private ApplicationUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RecipeService recipeService;

    @Override
    public ApplicationUser createUser(ApplicationUserRequest userRequest) {
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        ApplicationUser user = userRepository.createUser(userRequest);
        return userRepositoryInterface.save(user);
    }

    @Override
    public ApplicationUser getUser(Long id) {
        return userRepositoryInterface.findById(id).orElse(null);
    }

    @Override
    public ApplicationUser getUserByCookbook(Long id) {
        return userRepositoryInterface.findByCookbookId(id);
    }

    @Override
    public ApplicationUser getUserByFridge(Long id) {
        return userRepositoryInterface.findByFridgeId(id);
    }

    @Override
    public ApplicationUser getUserByRecipe(Long id) {
        Recipe recipe = recipeService.getRecipe(id);
        return userRepositoryInterface.findByRecipes(recipe);
    }

    @Override
    public ApplicationUserResponse getResponseUser(ApplicationUser user) throws Exception {
        if (user != null) {
            return new ApplicationUserResponse(
                    user.getEmail(),
                    user.getUsername(),
                    user.getFirstName(),
                    user.getLastName());
        } else {
            throw new Exception();
        }
    }

    @Override
    public ApplicationUser getUserByName(String username) {
        return userRepositoryInterface.findByUsername(username);
    }

    @Override
    public List<ApplicationUser> getUsers() {
        return userRepositoryInterface.findAll();
    }

    @Override
    public ApplicationUser updateUser(Long id, ApplicationUserRequest userRequest) {
        ApplicationUser originalUser = getUser(id);
        return userRepository.updateUser(originalUser, userRequest);
    }

    @Override
    public ApplicationUser updateUserAvatar(Long id, byte[] avatar) {
        ApplicationUser originalUser = getUser(id);
        return userRepository.updateUserAvatar(originalUser, avatar);
    }
}
