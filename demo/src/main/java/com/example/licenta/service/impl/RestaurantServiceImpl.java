package com.example.licenta.service.impl;

import com.example.licenta.exception.ApiException;
import com.example.licenta.model.Restaurant;
import com.example.licenta.model.dto.RestaurantDTO;
import com.example.licenta.repository.RestaurantRepository;
import com.example.licenta.service.RestaurantService;
import com.example.licenta.service.converter.RestaurantConverter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.licenta.exception.ErrorKeys.NOT_FOUND;
import static com.example.licenta.repository.FilterSpecifications.byRating;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<RestaurantDTO> findAll(Double rating) {
        Specification<Restaurant> specification = getRestaurantSpecification(rating);
        return restaurantRepository.findAll(specification).stream().map(RestaurantConverter::toRestaurantDTO).toList();
    }

    public RestaurantDTO findById(UUID id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new ApiException("Restaurant not found", NOT_FOUND, HttpStatus.NOT_FOUND));
        return RestaurantConverter.toRestaurantDTO(restaurant);
    }

    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = RestaurantConverter.toRestaurant(restaurantDTO);
        restaurant.setRating(0);
        return RestaurantConverter.toRestaurantDTO(restaurantRepository.save(restaurant));
    }

    public void deleteRestaurant(UUID id) {
        restaurantRepository.deleteById(id);
    }

    public RestaurantDTO modifyRestaurantDetails(UUID id, RestaurantDTO newRestaurantDTO) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();

        restaurant.setName(newRestaurantDTO.getName());
        restaurant.setAddress(newRestaurantDTO.getAddress());
        restaurant.setPhoneNumber(newRestaurantDTO.getPhoneNumber());

        return RestaurantConverter.toRestaurantDTO(restaurantRepository.save(restaurant));
    }

    private Specification<Restaurant> getRestaurantSpecification(Double rating) {
        Specification<Restaurant> specification = Specification.where(null);

        if (rating != null) {
            specification = byRating(rating).and(specification);
            return specification;
        }
        return null;
    }
}
