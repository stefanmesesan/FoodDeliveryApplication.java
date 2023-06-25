package com.example.licenta;

import com.example.licenta.model.Restaurant;
import com.example.licenta.model.User;
import com.example.licenta.model.UserRole;
import com.example.licenta.model.dto.RestaurantDTO;
import com.example.licenta.repository.MenuItemRepository;
import com.example.licenta.repository.OrderItemRepository;
import com.example.licenta.repository.OrderRepository;
import com.example.licenta.repository.RestaurantRepository;
import com.example.licenta.repository.UserRepository;
import com.example.licenta.service.EmailService;
import com.example.licenta.service.RestaurantService;
import com.example.licenta.service.impl.RestaurantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.licenta.utils.Constants.TEST_ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    private RestaurantService restaurantService;
    private Restaurant restaurant;
    private RestaurantDTO restaurantDTO;
    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private EmailService emailService;
    @Mock
    private MenuItemRepository menuItemRepository;
    @Mock
    private OrderItemRepository orderItemRepository;

    @BeforeEach
    public void initialize() {
        openMocks(this);

        restaurant = createRestaurant();
        restaurantDTO = createRestaurantDTO();

        restaurantService = new RestaurantServiceImpl(restaurantRepository, orderRepository, userRepository, emailService, menuItemRepository, orderItemRepository);
    }

    @Test
    public void createRestaurantTest() {
        when(userRepository.findUserByEmail("email")).thenReturn(Optional.of(createUser()));
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);
        when(restaurantRepository.existsByAddedBy(TEST_ID)).thenReturn(false);

        RestaurantDTO restaurantDTOTest = restaurantService.addRestaurant(restaurantDTO, "email");
        assertThat(restaurantDTOTest).usingRecursiveComparison().isEqualTo(restaurantDTO);
    }

    @Test
    public void modifyRestaurantTest() {
       when(restaurantRepository.findById(TEST_ID)).thenReturn(Optional.ofNullable(restaurant));
       when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

       RestaurantDTO newRestaurantDTO = restaurantService.modifyRestaurantDetails(TEST_ID, restaurantDTO);
       assertThat(newRestaurantDTO).usingRecursiveComparison().isEqualTo(restaurantDTO);
    }

    private Restaurant createRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(TEST_ID);
        restaurant.setName("name");
        restaurant.setAddress("address");
        restaurant.setRating(1.0);
        restaurant.setNeedDeletion(true);
        restaurant.setPhoneNumber("phone number");
        restaurant.setAddedBy(TEST_ID);
        return restaurant;
    }

    private RestaurantDTO createRestaurantDTO() {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(TEST_ID);
        restaurantDTO.setName("name");
        restaurantDTO.setAddress("address");
        restaurantDTO.setRating(1.0);
        restaurantDTO.setNeedDeletion(true);
        restaurantDTO.setPhoneNumber("phone number");
        restaurantDTO.setAddedBy(TEST_ID);
        return restaurantDTO;
    }

    private User createUser() {
        User user = new User();
        user.setId(TEST_ID);
        user.setFirstName("first name");
        user.setLastName("last name");
        user.setEmail("email");
        user.setPassword("password");
        user.setAddress("address");
        user.setRole(UserRole.CUSTOMER);
        return user;
    }
}
