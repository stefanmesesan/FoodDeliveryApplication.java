package com.example.licenta.controller;

import com.example.licenta.model.dto.MenuItemDTO;
import com.example.licenta.service.MenuItemService;
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

@RestController
@RequestMapping(path = "/menuItemController")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/{restaurantId}")
    public List<MenuItemDTO> getAllMenuItems(@PathVariable UUID restaurantId) {
        return menuItemService.findAllByRestaurantId(restaurantId);
    }

    @GetMapping("/{id}/ceva")
    public MenuItemDTO getMenuItem(@PathVariable UUID id) {
        return menuItemService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable UUID id) {
        menuItemService.deleteMenuItem(id);
    }

    @PostMapping
    public MenuItemDTO addMenuItem(@RequestBody MenuItemDTO menuItemDTO) {
        return menuItemService.addMenuItem(menuItemDTO);
    }

    @PutMapping("/{id}")
    public MenuItemDTO modifyRestaurant(@PathVariable(value = "id") UUID id,
                                        @RequestBody MenuItemDTO menuItemDTO) {
        return menuItemService.modifyMenuItem(id, menuItemDTO);
    }
}
