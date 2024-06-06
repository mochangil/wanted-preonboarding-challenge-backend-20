package preonboarding.market.order.service;


import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import preonboarding.market.member.entity.Member;
import preonboarding.market.member.repository.MemberRepository;
import preonboarding.market.order.dto.request.OrderApprovalReqeustDto;
import preonboarding.market.order.dto.request.OrderSaveRequestDto;
import preonboarding.market.order.entity.Orders;
import preonboarding.market.order.repository.OrderRepository;
import preonboarding.market.product.entity.Product;
import preonboarding.market.product.repository.ProductRepository;

import java.util.ConcurrentModificationException;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;



    public void saveOrders(final Long memberId, OrderSaveRequestDto orderSaveRequestDto){
        Member foundMember = memberRepository.findById(memberId).orElseThrow(()->{
            throw new IllegalArgumentException("해당 회원이 존재하지 않습니다");
        });

        Product foundProduct = productRepository.findById(orderSaveRequestDto.productId()).orElseThrow(()->{
            throw new IllegalArgumentException("해당 제품이 존재하지 않습니다.");
        });
        if(!foundProduct.validateForSale()){
            throw new ConcurrentModificationException("구매할 수 없는 상태입니다.");
        }
        Orders order = Orders.from(foundMember,foundProduct);
        orderRepository.save(order);
    }

    public void approveOrders(final Long memberId, OrderApprovalReqeustDto orderSaveRequestDto){
        Member foundMember = memberRepository.findById(memberId).orElseThrow(()->{
            throw new IllegalArgumentException("해당 회원이 존재하지 않습니다.");
        });
        Orders foundOrders = orderRepository.findById(orderSaveRequestDto.orderId()).orElseThrow(()->{
            throw new IllegalArgumentException("해당 주문이 존재하지 않습니다.");
        });
        foundOrders.changeStatus(Orders.OrderStatus.CONFIRMED);
        orderRepository.save(foundOrders);


    }

}
