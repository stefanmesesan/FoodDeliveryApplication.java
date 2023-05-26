package com.example.licenta.service;

import com.example.licenta.model.dto.MenuItemDTO;

import java.util.List;
import java.util.UUID;

public interface MenuItemService {

    List<MenuItemDTO> findAllByRestaurantId(UUID id);
    MenuItemDTO addMenuItem(MenuItemDTO menuItemDTO);
    void deleteMenuItem(UUID id);
    MenuItemDTO modifyMenuItem(UUID id, MenuItemDTO menuItemDTO);
    MenuItemDTO findById(UUID id);
}
