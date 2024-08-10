package com.sparta.msa_exam.product.domain.product.entity;

import com.sparta.msa_exam.product.domain.model.BaseEntity;
import com.sparta.msa_exam.product.domain.product.dto.request.AddProductReq;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "products") // 모든 테이블명은 엔티티명에 -s 붙이기
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA Entity 기본 생성자가 필요하므로, 최소 접근제어자로 제한
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "supply_price", nullable = false)
    private Integer supplyPrice;

    /**
     * 정적 팩토리 메서드로 생성
     */
    public static Product from(AddProductReq request) {

        Product product = new Product();

        product.name = request.name();
        product.supplyPrice = request.supply_price();

        return product;
    }
}
