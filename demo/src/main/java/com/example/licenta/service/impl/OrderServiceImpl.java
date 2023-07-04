package com.example.licenta.service.impl;

import com.example.licenta.exception.ApiException;
import com.example.licenta.model.MenuItem;
import com.example.licenta.model.Order;
import com.example.licenta.model.OrderItem;
import com.example.licenta.model.dto.OrderRequestDTO;
import com.example.licenta.model.OrderStatus;
import com.example.licenta.model.UserRole;
import com.example.licenta.model.dto.OrderDTO;
import com.example.licenta.repository.MenuItemRepository;
import com.example.licenta.repository.OrderItemRepository;
import com.example.licenta.repository.OrderRepository;
import com.example.licenta.service.OrderService;
import com.example.licenta.service.converter.OrderConverter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.licenta.exception.ErrorKeys.INVALID_STATUS;
import static com.example.licenta.exception.ErrorKeys.NOT_FOUND;
import static com.example.licenta.model.OrderStatus.DELIVERED;
import static com.example.licenta.model.OrderStatus.ON_ITS_WAY;
import static com.example.licenta.model.OrderStatus.ORDER_CANCELED;
import static com.example.licenta.model.OrderStatus.ORDER_RECEIVED;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, MenuItemRepository menuItemRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream().map(OrderConverter::toOrderDTO).toList();
    }

    public List<OrderDTO> findAllByStatus(OrderStatus orderStatus) {
        return orderRepository.findAllByOrderStatus(OrderStatus.ORDER_CANCELED).stream().map(OrderConverter::toOrderDTO).toList();
    }

    public List<OrderDTO> findAllByStatusOrderReceived() {
        return orderRepository.findAllByOrderStatus(ORDER_RECEIVED).stream().map(OrderConverter::toOrderDTO).toList();
    }

    public List<OrderDTO> findAllByStatusOnItsWay() {
        return orderRepository.findAllByOrderStatus(ON_ITS_WAY).stream().map(OrderConverter::toOrderDTO).toList();
    }

    public OrderDTO findById(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ApiException("Restaurant not found", NOT_FOUND, HttpStatus.NOT_FOUND));
        return OrderConverter.toOrderDTO(order);
    }

    public OrderDTO cancelOrder(UUID id, UserRole userRole) {
        Order order = orderRepository.findById(id).orElseThrow();
        if (userRole == UserRole.CUSTOMER)
            order.setOrderStatus(ORDER_CANCELED);
        return OrderConverter.toOrderDTO(orderRepository.save(order));
    }

    public OrderDTO acceptOrder(UUID id, UserRole userRole) {
        Order order = orderRepository.findById(id).orElseThrow();
        if (userRole == UserRole.RESTAURANT_OPERATOR)
            order.setOrderStatus(ORDER_RECEIVED);
        return OrderConverter.toOrderDTO(orderRepository.save(order));
    }

    public OrderDTO pickUpOrder(UUID id, UserRole userRole, UUID deliveryGuyId) {
        Order order = orderRepository.findById(id).orElseThrow();
        if (userRole == UserRole.DELIVERY_GUY) {
            order.setDeliveryGuyId(deliveryGuyId);
            order.setOrderStatus(ON_ITS_WAY);
        }
        return OrderConverter.toOrderDTO(orderRepository.save(order));
    }

    public OrderDTO deliverOrder(UUID id, UserRole userRole) {
        Order order = orderRepository.findById(id).orElseThrow();
        if (userRole == UserRole.DELIVERY_GUY)
            order.setOrderStatus(DELIVERED);
        return OrderConverter.toOrderDTO(orderRepository.save(order));
    }


    public void deleteOrder(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow();
        if (!order.getOrderStatus().equals(OrderStatus.ORDER_CANCELED))
            throw new ApiException("Invalid Order Status", INVALID_STATUS, HttpStatus.BAD_REQUEST);

        orderRepository.deleteById(id);
    }

    public OrderDTO placeNewOrder(OrderRequestDTO orderRequestDTO, UUID userId, UUID restaurantId) {
        List<UUID> menuItemIds = orderRequestDTO.getMenuItemIds();
        List<Integer> quantities = orderRequestDTO.getQuantities();

        Order order = new Order();
        order.setUserId(userId);
        order.setRestaurantId(restaurantId);
        order.setOrderStatus(OrderStatus.NEW);
        order.setCreatedAt(LocalDate.now());

        double totalPrice = calculateTotalPrice(menuItemIds, quantities);
        order.setTotalPrice(totalPrice);

        List<OrderItem> orderItems = createOrderItems(order.getId(), menuItemIds, quantities);
        orderItemRepository.saveAll(orderItems);

        return OrderConverter.toOrderDTO(orderRepository.save(order));
    }

    private double calculateTotalPrice(List<UUID> menuItemIds, List<Integer> quantities) {
        double totalPrice = 0.0;
        for (int i = 0; i < menuItemIds.size(); i++) {
            UUID menuItemId = menuItemIds.get(i);
            int quantity = quantities.get(i);
            MenuItem menuItem = menuItemRepository.findById(menuItemId).orElse(null);
            if (menuItem != null) {
                double itemPrice = menuItem.getPrice();
                totalPrice += itemPrice * quantity;
            }
        }
        return totalPrice;
    }

    private List<OrderItem> createOrderItems(UUID orderId, List<UUID> menuItemIds, List<Integer> quantities) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (int i = 0; i < menuItemIds.size(); i++) {
            UUID menuItemId = menuItemIds.get(i);
            int quantity = quantities.get(i);
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setMenuItemId(menuItemId);
            orderItem.setQuantity(quantity);
            orderItems.add(orderItem);
        }
        return orderItems;
    }
}
