package com.application.app.fridge;

import com.application.app.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
@RestController
@RequestMapping(value = "/fridge")
public class FridgeController {

    @Autowired
    private FridgeService fridgeService;

    @Autowired
    private SecurityService securityService;

    @GetMapping(value = "")
    public ResponseEntity<Fridge> getFridge(@RequestBody FridgeRequest fridgeRequest) {
        try {
            if (securityService.isSecuredGetFridge(fridgeRequest.getFridgeId()) == true) {
                Fridge response = fridgeService.getFridge(fridgeRequest.getFridgeId());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception();
            }
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<Fridge> updateFridge(@RequestBody FridgeIngredientRequest fridgeIngredientRequest) {
        try {
            if (securityService.isSecuredUpdateFridge(fridgeIngredientRequest.getFridgeId()) == true) {
                Fridge response = fridgeService.updateFridge(fridgeIngredientRequest.getFridgeId(),
                        fridgeIngredientRequest.getIngredientRequests());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new Exception();
            }
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
