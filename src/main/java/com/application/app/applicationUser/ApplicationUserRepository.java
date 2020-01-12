package com.application.app.applicationUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class ApplicationUserRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApplicationUser createUser(ApplicationUserRequest userRequest) {
        ApplicationUser user = new ApplicationUser();
        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());

        return user;
    }

    public ApplicationUser updateUser(ApplicationUser originalUser, ApplicationUserRequest userRequest) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        if (userRequest.getPassword() != null) {
            originalUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }
        if (userRequest.getEmail() != null) {
            originalUser.setEmail(userRequest.getEmail());
        }
        if (userRequest.getFirstName() != null) {
            originalUser.setFirstName(userRequest.getFirstName());
        }
        if (userRequest.getLastName() != null) {
            originalUser.setLastName(userRequest.getLastName());
        }

        entityManager.merge(originalUser);
        entityManager.getTransaction().commit();

        return originalUser;
    }

    public ApplicationUser updateUserAvatar(ApplicationUser originalUser, byte[] avatar) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        if (avatar != null) {
            originalUser.setAvatar(avatar);
        }

        entityManager.merge(originalUser);
        entityManager.getTransaction().commit();

        return originalUser;
    }
}
