package com.example.licenta.service.impl;

import com.example.licenta.exception.ApiException;
import com.example.licenta.exception.ErrorKeys;
import com.example.licenta.model.MenuItem;
import com.example.licenta.model.Order;
import com.example.licenta.model.OrderItem;
import com.example.licenta.model.OrderStatus;
import com.example.licenta.model.Restaurant;
import com.example.licenta.model.User;
import com.example.licenta.model.UserRole;
import com.example.licenta.model.dto.OrderDTO;
import com.example.licenta.model.dto.OrderItemDTO;
import com.example.licenta.repository.MenuItemRepository;
import com.example.licenta.repository.OrderItemRepository;
import com.example.licenta.repository.OrderRepository;
import com.example.licenta.repository.RestaurantRepository;
import com.example.licenta.repository.UserRepository;
import com.example.licenta.service.OrderService;
import com.example.licenta.service.converter.OrderConverter;
import com.example.licenta.service.converter.OrderItemConverter;
import com.example.licenta.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static com.example.licenta.exception.ErrorKeys.INVALID_STATUS;
import static com.example.licenta.exception.ErrorKeys.UNAUTHORIZED;
import static com.example.licenta.exception.ErrorKeys.NOT_FOUND;
import static com.example.licenta.model.OrderStatus.NEW;
import static com.example.licenta.model.OrderStatus.ON_ITS_WAY;
import static com.example.licenta.model.OrderStatus.ORDER_RECEIVED;
import static com.example.licenta.model.OrderStatus.ORDER_CANCELED;
import static com.example.licenta.utils.Constants.NOT_ENOUGH_AUTHORITIES;


@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream().map(OrderConverter::toOrderDTO).toList();
    }

    public List<OrderDTO> findAllByStatus(OrderStatus orderStatus) {
        return orderRepository.findAllByOrderStatus(OrderStatus.ORDER_CANCELED).stream().map(OrderConverter::toOrderDTO).toList();

    }

    public OrderDTO findById(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ApiException("Restaurant not found", NOT_FOUND, HttpStatus.NOT_FOUND));
        return OrderConverter.toOrderDTO(order);
    }

    public OrderItemDTO addToOrderItem(UUID menuItemId) {
        if(!menuItemRepository.existsById(menuItemId))
            throw new ApiException("blabla" , NOT_FOUND, HttpStatus.NOT_FOUND);

        Order order = new Order();

        OrderItem orderItem = new OrderItem();
        orderItem.setMenuItemId(menuItemId);
        orderItem.setQuantity(1);
        orderItem.setOrder(order.getId());

        return OrderItemConverter.toOrderItemDTO(orderItemRepository.save(orderItem));
    }

    public OrderDTO addOrder(UUID userId, UUID menuItemId) {
        OrderItemDTO orderItemDTOCreated = addToOrderItem(menuItemId);

        MenuItem menuItem = menuItemRepository.findById(orderItemDTOCreated.getMenuItem()).orElseThrow(() -> new ApiException(NOT_ENOUGH_AUTHORITIES, ErrorKeys.NOT_FOUND, HttpStatus.NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiException(Constants.USER_NOT_FOUND, ErrorKeys.NOT_FOUND, HttpStatus.NOT_FOUND));
        Restaurant restaurant = restaurantRepository.findById(menuItem.getRestaurantId()).orElseThrow(() -> new ApiException(NOT_ENOUGH_AUTHORITIES, ErrorKeys.NOT_FOUND, HttpStatus.NOT_FOUND));


        Order order = new Order();
        order.setOrderStatus(NEW);
        order.setCreatedAt(LocalDate.now());
        order.setUserId(user.getId());
        order.setRestaurantId(restaurant.getId());
        order.setTotalPrice(orderItemDTOCreated.getQuantity() * menuItem.getPrice().doubleValue());


        return OrderConverter.toOrderDTO(orderRepository.save(order));
    }

    public OrderDTO changeOrderStatus(UUID id, UserRole userRole) {
        Order order = orderRepository.findById(id).orElseThrow();
        if (userRole == UserRole.CUSTOMER)
            order.setOrderStatus(ORDER_CANCELED);
        if (order.getOrderStatus() != ORDER_CANCELED)
            if (userRole == UserRole.DELIVERY_GUY) {
                order.setOrderStatus(ON_ITS_WAY);
                return OrderConverter.toOrderDTO(orderRepository.save(order));
            } else if (userRole == UserRole.RESTAURANT_OPERATOR) {
                order.setOrderStatus(ORDER_RECEIVED);
            }
        throw new ApiException(NOT_ENOUGH_AUTHORITIES, UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
    }

    public OrderDTO modifyOrderDetails(UUID id, OrderDTO newOrder) {
        Order order = orderRepository.findById(id).orElseThrow();

        order.setRestaurantId(newOrder.getRestaurantId());
        order.setOrderStatus(newOrder.getOrderStatus());
        order.setTotalPrice(newOrder.getTotalPrice());

        return OrderConverter.toOrderDTO(orderRepository.save(order));
    }

    public void deleteOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow();
        if (!order.getOrderStatus().equals(OrderStatus.ORDER_CANCELED))
            throw new ApiException("Invalid Order Status", INVALID_STATUS, HttpStatus.BAD_REQUEST);

        orderRepository.deleteById(id);
    }

    private void addMenuItemToOrder(UUID menuItemId) {

    }
}
