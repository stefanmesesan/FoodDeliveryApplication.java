package com.example.licenta.service;

import com.example.licenta.model.dto.RestaurantDTO;

import java.util.List;
import java.util.UUID;

public interface RestaurantService {
    List<RestaurantDTO> findAll();
    RestaurantDTO addRestaurant(RestaurantDTO restaurant);
    void deleteRestaurant(UUID id);
    RestaurantDTO modifyRestaurantDetails(RestaurantDTO newRestaurant);
    RestaurantDTO findById(UUID id);

}
