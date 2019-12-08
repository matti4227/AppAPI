package com.application.app.applicationUser;

import com.application.app.cookbook.CookbookService;
import com.application.app.fridge.FridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApplicationUser> getUser(@PathVariable(value = "id") Long id) {
        try {
            ApplicationUser response = userService.getUser(id);
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

    @PatchMapping(value = "/users/edit/{id}")
    public ResponseEntity<ApplicationUser> updateUser(@RequestBody ApplicationUserRequest userRequest,
                                                      @PathVariable(value = "id") Long id) {
        try {
            ApplicationUser response = userService.updateUser(userRequest, id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
