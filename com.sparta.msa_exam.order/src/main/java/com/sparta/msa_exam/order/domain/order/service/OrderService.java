package com.sparta.msa_exam.order.domain.order.service;

import com.sparta.msa_exam.order.domain.order.dto.request.AddOrderItemReq;
import com.sparta.msa_exam.order.domain.order.dto.request.CreateOrderReq;
import com.sparta.msa_exam.order.domain.order.dto.response.AddOrderItemRes;
import com.sparta.msa_exam.order.domain.order.dto.response.CreateOrderRes;
import com.sparta.msa_exam.order.domain.order.dto.response.GetOrderRes;
import com.sparta.msa_exam.order.domain.order.entity.Order;
import com.sparta.msa_exam.order.domain.order.mapper.OrderMapper;
import com.sparta.msa_exam.order.domain.order.repository.OrderRepository;
import com.sparta.msa_exam.order.global.openfeign.client.ProductClient;
import com.sparta.msa_exam.order.global.openfeign.client.dto.response.GetProductsRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;

    /**
     * 주문 단건 조회
     */
    @Transactional(readOnly = true)
    public GetOrderRes getOrder(Long orderId) {

        Order order = findOrder(orderId);

        return orderMapper.toGetOrderReq(order);
    }

    private Order findOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * 주문 생성
     */
    @Transactional
    public CreateOrderRes createOrder(CreateOrderReq request) {

        Order order = Order.from(request);
        Order savedOrder = orderRepository.save(order);

        return orderMapper.toCreateOrderRes(savedOrder);
    }

    /**
     * 주문 아이템 추가
     */
    @Transactional
    public AddOrderItemRes addOrderItem(Long orderId, AddOrderItemReq request) {

        Order order = findOrder(orderId);

        // 상품 아이디를 담은 주문 아이템이 실제로 있는 상품인지를 검증
        checkValidProductId(request);
        // 주문에 주문 아이템 추가
        order.addOrderItem(request);

        return new AddOrderItemRes(orderId, request.productId());
    }

    private void checkValidProductId(AddOrderItemReq request) {

        List<GetProductsRes> productInfoList = productClient.getProducts();

        productInfoList.stream()
                .map(GetProductsRes::productId)
                .filter(productId -> productId.equals(request.productId()))
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
