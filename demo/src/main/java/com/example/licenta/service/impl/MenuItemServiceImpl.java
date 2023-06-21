package com.example.licenta.service.impl;

import com.example.licenta.exception.ApiException;
import com.example.licenta.model.MenuItem;
import com.example.licenta.model.dto.MenuItemDTO;
import com.example.licenta.repository.MenuItemRepository;
import com.example.licenta.repository.OrderItemRepository;
import com.example.licenta.service.MenuItemService;
import com.example.licenta.service.converter.MenuItemConverter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.licenta.exception.ErrorKeys.NOT_FOUND;

@Service
public class MenuItemServiceImpl implements MenuItemService {
    private final MenuItemRepository menuItemRepository;

    public MenuItemServiceImpl(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    public List<MenuItemDTO> findAllByRestaurantId(UUID id) {
        return menuItemRepository.findAllByRestaurantId(id).stream().map(MenuItemConverter::toMenuItemDTO).toList();
    }

    public MenuItemDTO findById(UUID id) {
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow(() -> new ApiException("Menu Item not found", NOT_FOUND, HttpStatus.NOT_FOUND));
        return MenuItemConverter.toMenuItemDTO(menuItem);
    }

    public MenuItemDTO addMenuItem(MenuItemDTO menuItemDTO) {
        MenuItem menuItem = MenuItemConverter.toMenuItem(menuItemDTO);
        return MenuItemConverter.toMenuItemDTO(menuItemRepository.save(menuItem));
    }

    public void deleteMenuItem(UUID id) {
        menuItemRepository.deleteById(id);
    }

    public MenuItemDTO modifyMenuItem(UUID id, MenuItemDTO menuItemDTO) {
        MenuItem menuItem = menuItemRepository.findById(id).orElseThrow();

        if (menuItemDTO.getName() != null)
            menuItem.setName(menuItemDTO.getName());
        if (menuItemDTO.getDescription() != null)
            menuItem.setDescription(menuItemDTO.getDescription());
        if (menuItemDTO.getPrice() != null)
            menuItem.setPrice(menuItemDTO.getPrice());

        return MenuItemConverter.toMenuItemDTO(menuItemRepository.save(menuItem));
    }
}
