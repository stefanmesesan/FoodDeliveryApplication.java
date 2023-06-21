package com.example.licenta.service.impl;

import com.example.licenta.exception.ApiException;
import com.example.licenta.exception.ErrorKeys;
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
import com.example.licenta.utils.Constants;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.licenta.repository.FilterSpecifications.byName;

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
        Specification<Restaurant> specification = getRestaurantSpecification(name);
        return restaurantRepository.findAll(specification).stream().map(RestaurantConverter::toRestaurantDTO).toList();
    }

    public List<RestaurantDTO> findAll() {
        return restaurantRepository.findAll().stream().map(RestaurantConverter::toRestaurantDTO).toList();
    }

    public RestaurantDTO findById(UUID id) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new ApiException("Restaurant not found", ErrorKeys.NOT_FOUND, HttpStatus.NOT_FOUND));
        return RestaurantConverter.toRestaurantDTO(restaurant);
    }

    public List<OrderDTO> findMyRestaurantOrders(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiException(Constants.USER_NOT_FOUND, ErrorKeys.NOT_FOUND, HttpStatus.NOT_FOUND));
        Restaurant restaurant = restaurantRepository.findByAddedBy(user.getId());
        return orderRepository.findAllByRestaurantId(restaurant.getId()).stream().map(OrderConverter::toOrderDTO).toList();
    }

    public List<OrderDTO> findMyOrders(UUID userId) {
        if (userRepository.findById(userId).isEmpty())
            throw new ApiException(Constants.USER_NOT_FOUND, ErrorKeys.NOT_FOUND, HttpStatus.NOT_FOUND);
        return orderRepository.findAllByUserId(userId).stream().map(OrderConverter::toOrderDTO).toList();

    }

    public void sendDeleteRequest(String userEmail) {
        User user = userRepository.findUserByEmail(userEmail).orElseThrow(() -> new ApiException(Constants.USER_NOT_FOUND, ErrorKeys.NOT_FOUND, HttpStatus.NOT_FOUND));
        Restaurant restaurant = restaurantRepository.findByAddedBy(user.getId());
        restaurant.setNeedDeletion(true);

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setSubject("Cerere de stergere Restaurant");
        emailDetails.setMessage("Buna ziua! Doresc stergerea restaurantului meu din aplicatia FUUD|EP.\nNumele restaurantului meu este " + restaurant.getName() + ". \nVa multumesc!");
        emailDetails.setRecipient("stefan.mesesan12@gmail.com");

        emailService.sendSimpleMail(emailDetails);
        RestaurantConverter.toRestaurantDTO(restaurantRepository.save(restaurant));
    }

    public RestaurantDTO addRestaurant(RestaurantDTO restaurantDTO, String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new ApiException(Constants.USER_NOT_FOUND, ErrorKeys.NOT_FOUND, HttpStatus.NOT_FOUND));
        if (restaurantRepository.existsByAddedBy(user.getId()))
            throw new ApiException("A fost adaugat deja un restaurant folosind acest cont!", ErrorKeys.ALREADY_EXISTS, HttpStatus.BAD_REQUEST);

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
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new ApiException("Restaurant not found", ErrorKeys.NOT_FOUND, HttpStatus.NOT_FOUND));

        if (newRestaurantDTO.getName() != null)
            restaurant.setName(newRestaurantDTO.getName());
        if (newRestaurantDTO.getAddress() != null)
            restaurant.setAddress(newRestaurantDTO.getAddress());
        if (newRestaurantDTO.getPhoneNumber() != null)
            restaurant.setPhoneNumber(newRestaurantDTO.getPhoneNumber());

        return RestaurantConverter.toRestaurantDTOandId(restaurantRepository.save(restaurant), restaurant.getAddedBy());
    }

    private Specification<Restaurant> getRestaurantSpecification(String name) {
        Specification<Restaurant> specification = Specification.where(null);

        if (name != null)
            specification = byName(name).and(specification);

        return specification;
    }
}
