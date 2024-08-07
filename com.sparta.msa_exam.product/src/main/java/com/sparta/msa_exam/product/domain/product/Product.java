package com.sparta.msa_exam.product.domain.product;

import com.sparta.msa_exam.product.domain.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
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
     * 생성 규칙은 빌더패턴으로 통일하고, 이외의 방식으로는 생성하지 못하게 제한
     */
    @Builder
    private Product(String name, Integer supplyPrice) {
        this.name = name;
        this.supplyPrice = supplyPrice;
    }
}
