package com.sparta.msa_exam.order.domain.order.entity;

import com.sparta.msa_exam.order.domain.model.BaseEntity;
import com.sparta.msa_exam.order.domain.order.dto.request.CreateOrderItemReq;
import com.sparta.msa_exam.order.domain.order.dto.request.CreateOrderReq;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "orders") // 모든 테이블명은 엔티티명에 -s 붙이기
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA Entity 기본 생성자가 필요하므로, 최소 접근제어자로 제한
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(
            mappedBy = "order",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<OrderItem> orderItemList = new ArrayList<>();

    /**
     * 정적 팩토리 메서드로 생성
     */
    public static Order from(CreateOrderReq request) {

        Order order = new Order();

        order.name = request.name();
        order.orderItemList = request.productIds()
                .stream()
                .map(productId -> new CreateOrderItemReq(order, productId))
                .map(OrderItem::from)
                .toList();

        return order;
    }
}
