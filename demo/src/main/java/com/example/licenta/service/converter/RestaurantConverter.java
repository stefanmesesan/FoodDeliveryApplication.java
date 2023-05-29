package com.example.licenta.service.converter;

import com.example.licenta.model.Restaurant;
import com.example.licenta.model.dto.RestaurantDTO;

public class RestaurantConverter {

    public static RestaurantDTO toRestaurantDTO(Restaurant restaurant){
        final RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setPhoneNumber(restaurant.getPhoneNumber());
        restaurantDTO.setRating(restaurant.getRating());
        restaurantDTO.setRestaurantStatus(restaurant.getRestaurantStatus());

        return restaurantDTO;
    }

    public static Restaurant toRestaurant(RestaurantDTO restaurantDTO){
        final Restaurant restaurant = new Restaurant();
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setId(restaurantDTO.getId());
        restaurant.setPhoneNumber(restaurantDTO.getPhoneNumber());
        restaurant.setRating(restaurantDTO.getRating());
        restaurant.setRestaurantStatus(restaurantDTO.getRestaurantStatus());

        return restaurant;
    }
}
