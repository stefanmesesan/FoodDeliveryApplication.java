package com.example.licenta.service;

import com.example.licenta.model.dto.RestaurantDTO;

import java.util.List;
import java.util.UUID;

public interface RestaurantService {
    List<RestaurantDTO> findAll(Double rating);
    RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO);
    void deleteRestaurant(UUID id);
    RestaurantDTO modifyRestaurantDetails(UUID id, RestaurantDTO newRestaurantDTO);
    RestaurantDTO findById(UUID id);

}
