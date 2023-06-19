package com.example.licenta.controller;

import com.example.licenta.model.User;
import com.example.licenta.model.UserRole;
import com.example.licenta.model.dto.RestaurantDTO;
import com.example.licenta.model.dto.ReviewDTO;
import com.example.licenta.security.Secured;
import com.example.licenta.service.RestaurantService;
import com.example.licenta.service.ReviewService;
import com.example.licenta.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final ReviewService reviewService;
    private final UserService userService;

    public RestaurantController(RestaurantService restaurantService, ReviewService reviewService, UserService userService) {
        this.restaurantService = restaurantService;
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @GetMapping("/search")
    public List<RestaurantDTO> searchRestaurant(@RequestParam(name = "name", required = false) String name) {

        return restaurantService.findByName(name);
    }

    @GetMapping("/admin")
    @Secured(role = {UserRole.ADMIN})
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.findAllNeedDeletion();
    }

    @GetMapping
    @Secured(role = {UserRole.CUSTOMER})
    public List<RestaurantDTO> getRestaurants(@RequestParam(name = "rating", required = false) Double rating) {
        return restaurantService.findAllBySpecifications(rating);
    }

    @GetMapping("/{id}")
    public RestaurantDTO getRestaurant(@PathVariable UUID id) {
        return restaurantService.findById(id);
    }

    @PostMapping("/deleteRequest")
    public void sendDeleteRestaurantRequest() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        restaurantService.sendDeleteRequest(userEmail);
    }

    @DeleteMapping("/{id}")
    @Secured(role = {UserRole.ADMIN})
    public void deleteRestaurant(@PathVariable UUID id) {
        restaurantService.deleteRestaurant(id);
    }

    @PostMapping
    public RestaurantDTO addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return restaurantService.addRestaurant(restaurantDTO, user.getEmail());
    }

    @PutMapping("/{id}")
    @Secured(role = {UserRole.RESTAURANT_OPERATOR})
    public RestaurantDTO modifyRestaurant(@PathVariable(value = "id") UUID id,
                                          @RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.modifyRestaurantDetails(id, restaurantDTO);
    }

    @PutMapping("/rating/{userId}/{restaurantId}")
    public ReviewDTO giveRating(@PathVariable(value = "userId") UUID userId,
                                @PathVariable(value = "restaurantId") UUID restaurantId,
                                @RequestBody ReviewDTO reviewDTO) {
        return reviewService.placeReview(reviewDTO, userId, restaurantId);
    }
}
