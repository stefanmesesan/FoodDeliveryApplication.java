package com.example.licenta.controller;

import com.example.licenta.model.dto.RestaurantDTO;
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
import java.util.UUID;

@RestController
@RequestMapping(path = "/controller")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.findAll();
    }

    @GetMapping("/{id}")
    public RestaurantDTO getRestaurant(@PathVariable UUID id) {
        return restaurantService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable UUID id) {
        restaurantService.deleteRestaurant(id);
    }

    @PostMapping
    public RestaurantDTO addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.addRestaurant(restaurantDTO);
    }

    @PutMapping
    public RestaurantDTO modifyRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.modifyRestaurantDetails(restaurantDTO);
    }
}
