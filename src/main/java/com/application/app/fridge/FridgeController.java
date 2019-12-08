package com.application.app.fridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/fridge")
public class FridgeController {

    @Autowired
    private FridgeService fridgeService;

    @GetMapping(value = "")
    public ResponseEntity<Fridge> getFridge(@RequestBody FridgeRequest fridgeRequest) {
        try {
            Fridge response = fridgeService.getFridge(fridgeRequest.getFridgeId());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<Fridge> updateFridge(@RequestBody FridgeIngredientRequest fridgeIngredientRequest) {
        try {
            Fridge response = fridgeService.updateFridge(fridgeIngredientRequest.getFridgeId(),
                    fridgeIngredientRequest.getIngredientRequests());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception er) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
