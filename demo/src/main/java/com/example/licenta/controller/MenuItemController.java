package com.example.licenta.controller;

import com.example.licenta.model.dto.MenuItemDTO;
import com.example.licenta.service.MenuItemService;
import com.example.licenta.service.OrderItemService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping(path = "/menuItems")
public class MenuItemController {

    private final MenuItemService menuItemService;
    private final OrderItemService orderItemService;

    public MenuItemController(MenuItemService menuItemService, OrderItemService orderItemService) {
        this.menuItemService = menuItemService;
        this.orderItemService = orderItemService;
    }

    @GetMapping("/getAll/{restaurantId}")
    public List<MenuItemDTO> getAllMenuItems(@PathVariable(value = "restaurantId") UUID restaurantId) {
        return menuItemService.findAllByRestaurantId(restaurantId);
    }

    @GetMapping("/{id}")
    public MenuItemDTO getMenuItem(@PathVariable(value = "id") UUID id) {
        return menuItemService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable UUID id) {
        orderItemService.deleteByMenuItem(id);
        menuItemService.deleteMenuItem(id);
    }

    @PostMapping
    public MenuItemDTO addMenuItem(@RequestBody MenuItemDTO menuItemDTO) {
        return menuItemService.addMenuItem(menuItemDTO);
    }

    @PutMapping("/{id}")
    public MenuItemDTO modifyMenuItem(@PathVariable(value = "id") UUID id,
                                      @RequestBody MenuItemDTO menuItemDTO) {
        return menuItemService.modifyMenuItem(id, menuItemDTO);
    }
}
