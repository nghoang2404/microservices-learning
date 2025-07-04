package com.nghoanghenry.microservices.order.repository;

import com.nghoanghenry.microservices.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
