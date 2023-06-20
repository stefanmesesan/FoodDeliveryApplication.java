package com.example.licenta.service;

import com.example.licenta.model.dto.OrderDTO;
import com.example.licenta.model.dto.RestaurantDTO;

import java.util.List;
import java.util.UUID;

public interface RestaurantService {
    RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO, String email);
    void deleteRestaurant(UUID id);
    RestaurantDTO modifyRestaurantDetails(UUID id, RestaurantDTO newRestaurantDTO);
    RestaurantDTO findById(UUID id);
    List<OrderDTO> findMyRestaurantOrders(UUID userId);
    List<OrderDTO> findMyOrders(UUID id);
    void sendDeleteRequest(String email);
    List<RestaurantDTO> findAllNeedDeletion();

    List<RestaurantDTO> findByName(String name);

    List<RestaurantDTO> findAll();
}
