package com.sparta.msa_exam.order.domain.order.repository;

import com.sparta.msa_exam.order.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
