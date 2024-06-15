package preonboarding.market.order.dto.response;

import preonboarding.market.order.entity.Orders;
import java.time.LocalDateTime;

public record OrderResponseDto(
        String productName,
        Long price,
        LocalDateTime createdAt
) {public OrderResponseDto(Orders orders){
    this(orders.getProduct().getName(),orders.getProduct().getPrice(),orders.getCreatedAt());
}
}
