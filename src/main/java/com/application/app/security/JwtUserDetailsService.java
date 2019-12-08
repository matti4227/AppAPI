package com.application.app.security;

import com.application.app.applicationUser.ApplicationUser;
import com.application.app.applicationUser.ApplicationUserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private ApplicationUserRepositoryInterface applicationUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);

        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }
}