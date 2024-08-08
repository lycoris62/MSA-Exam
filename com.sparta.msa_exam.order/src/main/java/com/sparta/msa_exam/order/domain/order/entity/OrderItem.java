package com.sparta.msa_exam.order.domain.order.entity;


import com.sparta.msa_exam.order.domain.model.BaseEntity;
import com.sparta.msa_exam.order.domain.order.dto.request.CreateOrderItemReq;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "order_items") // 모든 테이블명은 엔티티명에 -s 붙이기
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA Entity 기본 생성자가 필요하므로, 최소 접근제어자로 제한
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    /**
     * 정적 팩토리 메서드로 생성
     */
    public static OrderItem from(CreateOrderItemReq request) {

        OrderItem orderItem = new OrderItem();

        orderItem.order = request.order();
        orderItem.productId = request.productId();

        return orderItem;
    }
}
