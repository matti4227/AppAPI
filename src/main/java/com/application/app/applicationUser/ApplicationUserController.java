package com.application.app.applicationUser;

import com.application.app.cookbook.CookbookService;
import com.application.app.fridge.FridgeService;
import com.application.app.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.application.app.security.SecurityConstants.SIGN_UP_URL;

@RestController
@RequestMapping
public class ApplicationUserController {

    @Autowired
    private ApplicationUserService userService;

    @Autowired
    private CookbookService cookbookService;

    @Autowired
    private FridgeService fridgeService;

    @Autowired
    private SecurityService securityService;

    @PostMapping(value = SIGN_UP_URL)
    public ResponseEntity<?> createUser(@RequestBody ApplicationUserRequest userRequest) {
        try {
            ApplicationUser userCreated = userService.createUser(userRequest);
            cookbookService.createCookbook(userCreated.getId());
            fridgeService.createFridge(userCreated.getId());

            return new ResponseEntity<>(userCreated, HttpStatus.OK);
        } catch(Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<ApplicationUserResponse> getUser(@PathVariable(value = "id") Long id) {
        try {
            ApplicationUser user = userService.getUser(id);
            ApplicationUserResponse response = userService.getResponseUser(user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<ApplicationUser>> getUsers() {
        try {
            List<ApplicationUser> response = userService.getUsers();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/users/{id}/edit")
    public ResponseEntity<ApplicationUser> updateUser(@PathVariable(value = "id") Long id,
                                                      @RequestBody ApplicationUserRequest userRequest) {
        try {
            if (securityService.isSecuredUpdateUser(id) == true) {
                ApplicationUser response = userService.updateUser(id, userRequest);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception();
            }
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/users/{id}/avatar")
    public ResponseEntity<ApplicationUser> updateUserAvatar(@RequestParam(value = "file") MultipartFile file,
                                                            @PathVariable(value = "id") Long id) {
        try {
            if (securityService.isSecuredUpdateUserAvatar(id) == true) {
                ApplicationUser response = userService.updateUserAvatar(id, file.getBytes());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception();
            }
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
