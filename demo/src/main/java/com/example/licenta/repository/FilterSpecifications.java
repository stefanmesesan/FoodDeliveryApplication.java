package com.example.licenta.repository;

import com.example.licenta.model.Restaurant;
import org.springframework.data.jpa.domain.Specification;

public class FilterSpecifications {
    public static Specification<Restaurant> byName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }
}
