package com.example.licenta.controller;

import com.example.licenta.model.Restaurant;
import com.example.licenta.service.RestaurantService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/controller")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Restaurant> getRestaurant(@PathVariable UUID id) {
        return restaurantService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable UUID id) {
        restaurantService.deleteRestaurant(id);
    }

    @PostMapping
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }

    @PutMapping
    public Restaurant modifyRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.modifyRestaurantDetails(restaurant);
    }
}
