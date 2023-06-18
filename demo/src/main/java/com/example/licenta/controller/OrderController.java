package com.example.licenta.controller;

import com.example.licenta.model.UserRole;
import com.example.licenta.model.dto.OrderDTO;
import com.example.licenta.model.dto.UserDTO;
import com.example.licenta.service.OrderService;
import com.example.licenta.service.RestaurantService;
import com.example.licenta.service.UserService;
import com.example.licenta.utils.UserRoleMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/orderController")
public class OrderController {

    private final OrderService orderService;
    private final RestaurantService restaurantService;
    private final UserService userService;

    public OrderController(OrderService orderService, RestaurantService restaurantService, UserService userService) {
        this.orderService = orderService;
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/myOrders")
    public List<OrderDTO> getMyOrders() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
        UserDTO userDTO = userService.findByUserEmail(userEmail);

        return restaurantService.findMyRestaurantsOrders(userDTO.getId());
    }

    @GetMapping("/{id}")
    public OrderDTO getOrder(@PathVariable(value = "id") UUID id) {
        return orderService.findById(id);
    }

    @PutMapping("/{id}")
    public OrderDTO changeOrderStatus(@PathVariable(value = "id") UUID id) {
        Collection<? extends GrantedAuthority> userRole = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        UserRole userRoleMapped = UserRoleMapper.convert(userRole);

        return orderService.changeOrderStatus(id, userRoleMapped);
    }

    @PostMapping
    public OrderDTO addOrder(@RequestBody OrderDTO orderDTO) {
        return orderService.addOrder(orderDTO);
    }

    @PutMapping("/{id}/details")
    public OrderDTO modifyOrderDetails(@PathVariable(value = "id") UUID id,
                                       @RequestBody OrderDTO orderDTO) {
        return orderService.modifyOrderDetails(id, orderDTO);
    }
}
