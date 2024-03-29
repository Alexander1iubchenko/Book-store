package com.example.application.repository;

import com.example.application.model.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "orderItems")
    List<Order> findAllByUserId(Long id, Pageable pageable);

    @EntityGraph(attributePaths = "orderItems")
    Optional<Order> findOrderByIdAndUserId(Long id, Long userId);
}
