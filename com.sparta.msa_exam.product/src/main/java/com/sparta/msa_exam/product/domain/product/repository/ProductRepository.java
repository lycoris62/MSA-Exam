package com.sparta.msa_exam.product.domain.product.repository;

import com.sparta.msa_exam.product.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
