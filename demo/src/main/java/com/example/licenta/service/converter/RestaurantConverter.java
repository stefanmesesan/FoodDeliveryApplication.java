package com.example.licenta.service.converter;

import com.example.licenta.model.Restaurant;
import com.example.licenta.model.dto.RestaurantDTO;

public class RestaurantConverter {

    public static RestaurantDTO toRestaurantDTO(Restaurant resturant){
        final RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setAddress(resturant.getAddress());
        restaurantDTO.setName(resturant.getName());
        restaurantDTO.setId(resturant.getId());
        restaurantDTO.setPhoneNumber(restaurantDTO.getPhoneNumber());

        return restaurantDTO;
    }

    public static Restaurant toRestaurant(RestaurantDTO restaurantDTO){
        final Restaurant restaurant = new Restaurant();
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setId(restaurantDTO.getId());
        restaurant.setPhoneNumber(restaurantDTO.getPhoneNumber());

        return restaurant;
    }
}
