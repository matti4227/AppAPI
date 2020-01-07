package com.application.app.applicationUser;

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

//    @Autowired
//    private CookbookRepositoryInterface cookbookRepositoryInterface;

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
        //być może stworzyć domyślną wartość
    }

    @Override
    public List<ApplicationUser> getUsers() {
        return userRepositoryInterface.findAll();
        //do zmiany
    }

    @Override
    public ApplicationUser updateUser(ApplicationUserRequest userRequest) {
        ApplicationUser originalUser = getUserByName(userRequest.getUsername());
        return userRepository.updateUser(originalUser, userRequest);
    }

    @Override
    public ApplicationUser updateUserAvatar(Long id, byte[] avatar) {
        ApplicationUser originalUser = getUser(id);
        return userRepository.updateUserAvatar(originalUser, avatar);
    }
}
