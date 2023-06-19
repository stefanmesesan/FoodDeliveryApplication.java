package com.example.licenta.service.converter;

import com.example.licenta.model.Restaurant;
import com.example.licenta.model.dto.RestaurantDTO;

import java.util.UUID;

public class RestaurantConverter {

    public static RestaurantDTO toRestaurantDTOandId(Restaurant restaurant, UUID id) {
        final RestaurantDTO restaurantDTO = new RestaurantDTO();

        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setPhoneNumber(restaurant.getPhoneNumber());
        restaurantDTO.setRating(restaurant.getRating());
        restaurantDTO.setNeedDeletion(restaurant.getNeedDeletion());
        restaurantDTO.setAddedBy(id);

        return restaurantDTO;
    }

    public static RestaurantDTO toRestaurantDTO(Restaurant restaurant) {
        final RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setPhoneNumber(restaurant.getPhoneNumber());
        restaurantDTO.setRating(restaurant.getRating());
        restaurantDTO.setNeedDeletion(restaurant.getNeedDeletion());
        restaurantDTO.setAddedBy(restaurant.getAddedBy());

        return restaurantDTO;
    }

    public static Restaurant toRestaurant(RestaurantDTO restaurantDTO, UUID id) {
        final Restaurant restaurant = new Restaurant();
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setId(restaurantDTO.getId());
        restaurant.setPhoneNumber(restaurantDTO.getPhoneNumber());
        restaurant.setRating(restaurantDTO.getRating());
        restaurant.setNeedDeletion(restaurantDTO.getNeedDeletion());
        restaurant.setAddedBy(id);

        return restaurant;
    }
}
