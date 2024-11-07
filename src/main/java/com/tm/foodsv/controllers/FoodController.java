package com.tm.foodsv.controllers;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GooglePublicKeysManager;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.tm.foodsv.entities.Category;
import com.tm.foodsv.entities.Food;
import com.tm.foodsv.entities.NovaClasification;
import com.tm.foodsv.services.FoodService;
import com.tm.foodsv.util.PageDTO;

import org.springframework.data.domain.PageRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @Value("${panel.admin.email}")
    private String adminEmail;

    @Value("${google.client.id}")
    private String clientId;

    
    @GetMapping
    public PageDTO<Food> getFoods(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<Food> pageReq = foodService.getAllFoods(PageRequest.of(page, size));
        return new PageDTO<Food>(pageReq);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addFood(@Valid @RequestBody Food food, @RequestHeader("Authorization") String authHeader) {
 GoogleIdTokenVerifier verifier = new
        GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
        .setAudience(Collections.singletonList(clientId))
        .build();

        String token = authHeader.replace("Bearer ", "");
        System.out.println("this is the token receive raw "+ token);
        System.out.println("the clientid is"+ clientId);
        System.out.println("the admin email is "+ adminEmail);

            GoogleIdToken idToken;
            String email;

            try {
                
            idToken = verifier.verify(token);
            System.out.println("this is the id token created  "+ idToken);
            } catch (Exception e) {
               return ResponseEntity.badRequest().body("Internal error"+ e.toString());
            }

            if(idToken != null){

             GoogleIdToken.Payload payload = idToken.getPayload();
            email = payload.getEmail();
            System.out.println("the email is "+ email);

            if (email.equals(adminEmail)){
                foodService.addFood(food);
                return ResponseEntity.ok("Food Added");

            }else{
                    System.out.println("the email is "+ email);
                return ResponseEntity.badRequest().body("Error");
            }
            }else{
                return ResponseEntity.badRequest().body("idtoken is null");
            }
    }

    /* @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/update")
    public void updateFood(@RequestBody Food food) {
        foodService.updateFood(food);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteFood(@PathVariable int id) {
        foodService.deleteFoodById(id);
    }

    @GetMapping("/name/{name}")
    public List<Food> getFoodByName(@PathVariable String name) {
        List<Food> foods = foodService.getFoodByName(name);
        if (foods.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found");
        } else {
            return foods;
        }
    }

    @GetMapping("/category/{category}")
    public List<Food> getFoodByCategory(@PathVariable Category category) {
        List<Food> foods = foodService.getFoodByCategory(category);
        if (foods.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No food found in this category");
        } else {
            return foods;
        }
    }

    @GetMapping("/group/{novaClasification}")
    public List<Food> getFoodByNovaClasification(@PathVariable NovaClasification novaClasification) {
        List<Food> foods = foodService.getFoodByNovaClasification(novaClasification);
        if (foods.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No food found in this nova group");
        } else {
            return foods;
        }
    } */

    @GetMapping("/search")
    public PageDTO<Food> getFoodByNameContainingAndCategory(@RequestParam String name,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
            @RequestParam List<Category> categories) {
        if (categories.isEmpty()) {
            Page<Food> pageReq = foodService.getFoodByNameContaining(name, PageRequest.of(page, size));
            return new PageDTO<Food>(pageReq);
        }
        Page<Food> pageReq = foodService.getFoodByNameContainingAndCategory(name, PageRequest.of(page, size),
                categories);
        return new PageDTO<Food>(pageReq);
    }
}
