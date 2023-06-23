package com.example.licenta.controller;

import com.example.licenta.model.User;
import com.example.licenta.model.UserRole;
import com.example.licenta.model.dto.RestaurantDTO;
import com.example.licenta.model.dto.ReviewDTO;
import com.example.licenta.security.Secured;
import com.example.licenta.service.RestaurantService;
import com.example.licenta.service.ReviewService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    public RestaurantController(RestaurantService restaurantService, ReviewService reviewService) {
        this.restaurantService = restaurantService;
        this.reviewService = reviewService;
    }

    @GetMapping("/search")
    @Secured(role = {UserRole.CUSTOMER})
    public List<RestaurantDTO> searchRestaurant(@RequestParam(name = "name", required = false) String name) {
        return restaurantService.findByName(name);
    }

    @GetMapping("/admin")
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.findAllNeedDeletion();
    }

    @GetMapping
    public List<RestaurantDTO> getRestaurants() {
        return restaurantService.findAll();
    }

    @GetMapping("/{id}")
    public RestaurantDTO getRestaurant(@PathVariable UUID id) {
        return restaurantService.findById(id);
    }

    @GetMapping("/myRestaurant")
    public RestaurantDTO getMyRestaurant(@AuthenticationPrincipal User user) {
        return restaurantService.findMyRestaurant(user.getId());
    }

    @PostMapping("/deleteRequest")
    public void sendDeleteRestaurantRequest(@AuthenticationPrincipal User user) {
        restaurantService.sendDeleteRequest(user.getEmail());
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable UUID id) {
        restaurantService.deleteRestaurant(id);
    }

    @PostMapping
    public RestaurantDTO addRestaurant(@RequestBody RestaurantDTO restaurantDTO, @AuthenticationPrincipal User user) {
        return restaurantService.addRestaurant(restaurantDTO, user.getEmail());
    }

    @PutMapping("/{id}")
    public RestaurantDTO modifyRestaurant(@PathVariable(value = "id") UUID id,
                                          @RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.modifyRestaurantDetails(id, restaurantDTO);
    }

    @PutMapping("/reviews/{restaurantId}")
    public ReviewDTO placeReview(@AuthenticationPrincipal User user,
                                 @PathVariable(value = "restaurantId") UUID restaurantId,
                                 @RequestBody ReviewDTO reviewDTO) {
        return reviewService.placeReview(reviewDTO, user.getId(), restaurantId);
    }

    @GetMapping("/reviews")
    public List<ReviewDTO> getAllReviews() {
        return reviewService.findAll();
    }
}
