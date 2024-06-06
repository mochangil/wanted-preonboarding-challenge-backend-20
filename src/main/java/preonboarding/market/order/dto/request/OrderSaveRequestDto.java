package preonboarding.market.order.dto.request;

import preonboarding.market.order.entity.Orders;

public record OrderSaveRequestDto(
        Long productId
) { public OrderSaveRequestDto(Orders order){
    this(order.getProduct().getId());
}
}
