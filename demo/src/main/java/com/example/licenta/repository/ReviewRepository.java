package com.example.licenta.repository;

import com.example.licenta.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID>,JpaSpecificationExecutor<Review> {

}
