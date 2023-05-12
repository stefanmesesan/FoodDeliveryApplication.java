package com.example.licenta.service.converter;

import com.example.licenta.model.MenuItem;
import com.example.licenta.model.dto.MenuItemDTO;

public class MenuItemConverter {

    public static MenuItemDTO toMenuItemDTO(MenuItem menuItem) {
        final MenuItemDTO menuItemDTO = new MenuItemDTO();
        menuItemDTO.setDescription(menuItem.getDescription());
        menuItemDTO.setPrice(menuItem.getPrice());
        menuItemDTO.setName(menuItem.getName());
        menuItemDTO.setId(menuItem.getId());
        menuItemDTO.setRestaurant(menuItem.getRestaurant());

        return menuItemDTO;
    }


    public static MenuItem toMenuItem(MenuItemDTO menuItemDTO) {
        final MenuItem menuItem = new MenuItem();
        menuItem.setDescription(menuItemDTO.getDescription());
        menuItem.setPrice(menuItemDTO.getPrice());
        menuItem.setId(menuItemDTO.getId());
        menuItem.setRestaurant(menuItemDTO.getRestaurant());
        menuItem.setName(menuItemDTO.getName());

        return menuItem;
    }
}
