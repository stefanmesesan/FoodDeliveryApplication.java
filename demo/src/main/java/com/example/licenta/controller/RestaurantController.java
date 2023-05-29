package com.example.licenta.controller;

import com.example.licenta.model.Status;
import com.example.licenta.model.UserRole;
import com.example.licenta.model.dto.RestaurantDTO;
import com.example.licenta.security.Secured;
import com.example.licenta.service.RestaurantService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/admin")
    @Secured(role = {UserRole.ADMIN})
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.findAll();
    }

    @GetMapping()
    @Secured(role = {UserRole.CUSTOMER})
    public List<RestaurantDTO> getRestaurants(@RequestParam(value = "rating", required = false) Double rating) {
        return restaurantService.findAllBySpecifications(rating);
    }

    @GetMapping("/{id}")
    @Secured(role = {UserRole.CUSTOMER, UserRole.ADMIN})
    public RestaurantDTO getRestaurant(@PathVariable UUID id) {
        return restaurantService.findById(id);
    }

    @DeleteMapping("/{id}")
    @Secured(role = {UserRole.ADMIN})
    public void deleteRestaurant(@PathVariable UUID id) {
        restaurantService.deleteRestaurant(id);
    }

    @PostMapping
    @Secured(role = {UserRole.RESTAURANT_OPERATOR})
    public RestaurantDTO addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.addRestaurant(restaurantDTO);
    }

    @PutMapping("/{id}")
    @Secured(role = {UserRole.RESTAURANT_OPERATOR})
    public RestaurantDTO modifyRestaurant(@PathVariable(value = "id") UUID id,
                                          @RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.modifyRestaurantDetails(id, restaurantDTO);
    }

    @PutMapping("/status/{id}")
    @Secured(role = {UserRole.ADMIN})
    public RestaurantDTO changeRestaurantStatus(@RequestParam(value = "approved", required = false) Status restaurantStatus,
                                                @PathVariable(value = "id") UUID id) {
        return restaurantService.changeRestaurantStatus(restaurantStatus, id);
    }
}
