package com.example.licenta.service.impl;

import com.example.licenta.exception.ApiException;
import com.example.licenta.model.EmailDetails;
import com.example.licenta.model.Restaurant;
import com.example.licenta.model.User;
import com.example.licenta.model.dto.OrderDTO;
import com.example.licenta.model.dto.RestaurantDTO;
import com.example.licenta.repository.OrderRepository;
import com.example.licenta.repository.RestaurantRepository;
import com.example.licenta.repository.UserRepository;
import com.example.licenta.service.EmailService;
import com.example.licenta.service.RestaurantService;
import com.example.licenta.service.converter.OrderConverter;
import com.example.licenta.service.converter.RestaurantConverter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.licenta.exception.ErrorKeys.NOT_FOUND;
import static com.example.licenta.repository.FilterSpecifications.byName;
import static com.example.licenta.repository.FilterSpecifications.byRating;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, OrderRepository orderRepository, UserRepository userRepository, EmailService emailService) {
        this.restaurantRepository = restaurantRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public List<RestaurantDTO> findAllNeedDeletion() {
        return restaurantRepository.findAllByNeedDeletion(true).stream().map(RestaurantConverter::toRestaurantDTO).toList();
    }

    public List<RestaurantDTO> findByName(String name) {
        Specification<Restaurant> specification = getRestaurantSpecification(null, name);
        return restaurantRepository.findAll(specification).stream().map(RestaurantConverter::toRestaurantDTO).toList();
    }

    public List<RestaurantDTO> findAllBySpecifications(Double rating) {
        Specification<Restaurant> specification = getRestaurantSpecification(rating, null);
        return restaurantRepository.findAll(specification).stream().map(RestaurantConverter::toRestaurantDTO).toList();
    }

    public RestaurantDTO findById(UUID id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new ApiException("Restaurant not found", NOT_FOUND, HttpStatus.NOT_FOUND));
        return RestaurantConverter.toRestaurantDTO(restaurant);
    }

    public List<OrderDTO> findMyRestaurantOrders(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiException("User not found", NOT_FOUND, HttpStatus.NOT_FOUND));
        Restaurant restaurant = restaurantRepository.findByAddedBy(user);
        return orderRepository.findAllByRestaurantId(restaurant.getId()).stream().map(OrderConverter::toOrderDTO).toList();
    }

    public List<OrderDTO> findMyOrders(UUID userId) {
        if (userRepository.findById(userId).isEmpty())
            throw new ApiException("User not found", NOT_FOUND, HttpStatus.NOT_FOUND);
        return orderRepository.findAllByUserId(userId).stream().map(OrderConverter::toOrderDTO).toList();

    }

    public void sendDeleteRequest(String userEmail) {
        User user = userRepository.findUserByEmail(userEmail).orElseThrow(() -> new ApiException("User not found", NOT_FOUND, HttpStatus.NOT_FOUND));
        Restaurant restaurant = restaurantRepository.findByAddedBy(user);
        restaurant.setNeedDeletion(true);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setSubject("Cerere de stergere Restaurant");
        emailDetails.setMessage("Buna ziua! Doresc stergerea restaurantului meu din aplicatia FUUD|EP.\nNumele restaurantului meu este " + restaurant.getName() + ". \nVa multumesc!");

        emailService.sendSimpleMail(emailDetails);
    }

    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO, String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new ApiException("User not found", NOT_FOUND, HttpStatus.NOT_FOUND));

        Restaurant restaurant = RestaurantConverter.toRestaurant(restaurantDTO, user.getId());

        restaurant.setRating(0.0);
        restaurant.setNeedDeletion(false);
        restaurant.setAddedBy(user.getId());

        return RestaurantConverter.toRestaurantDTO(restaurantRepository.save(restaurant));
    }

    public void deleteRestaurant(UUID id) {
        restaurantRepository.deleteById(id);
    }

    public RestaurantDTO modifyRestaurantDetails(UUID id, RestaurantDTO newRestaurantDTO) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new ApiException("Restaurant not found", NOT_FOUND, HttpStatus.NOT_FOUND));

        restaurant.setName(newRestaurantDTO.getName());
        restaurant.setAddress(newRestaurantDTO.getAddress());
        restaurant.setPhoneNumber(newRestaurantDTO.getPhoneNumber());

        return RestaurantConverter.toRestaurantDTOandId(restaurantRepository.save(restaurant), restaurant.getAddedBy());
    }

    private Specification<Restaurant> getRestaurantSpecification(Double rating, String name) {
        Specification<Restaurant> specification = Specification.where(null);

        if (rating != null)
            specification = byRating(rating).and(specification);
        if (name != null)
            specification = byName(name).and(specification);

        return specification;
    }
}
