package com.example.licenta.repository;

import com.example.licenta.model.Restaurant;
import com.example.licenta.model.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<Restaurant, UUID>, JpaSpecificationExecutor<Restaurant> {

    Restaurant findByAddedBy(User addedBy);

    Restaurant findByName(String name);
}
