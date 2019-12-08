package com.application.app.applicationUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationUserRepositoryInterface extends JpaRepository<ApplicationUser, Long> {

    ApplicationUser findByUsername(String username);
    Optional<ApplicationUser> findById(Long id);
}
