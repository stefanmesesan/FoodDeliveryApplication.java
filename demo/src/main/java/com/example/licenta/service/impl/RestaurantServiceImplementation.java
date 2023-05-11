package com.example.licenta.service.impl;

import com.example.licenta.model.Restaurant;
import com.example.licenta.repository.RestaurantRepository;
import com.example.licenta.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestaurantServiceImplementation implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImplementation(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> findById(UUID id) {
        return restaurantRepository.findById(id);
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(UUID id) {
        restaurantRepository.deleteById(id);
    }

    public Restaurant modifyRestaurantDetails(Restaurant newRestaurant) {
        Restaurant restaurant = restaurantRepository.findById(newRestaurant.getId()).orElseThrow();

        restaurant.setName(newRestaurant.getName());
        restaurant.setAddress(newRestaurant.getAddress());
        restaurant.setPhoneNumber(newRestaurant.getPhoneNumber());

        return restaurantRepository.save(restaurant);
    }
}
