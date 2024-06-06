package preonboarding.market.product.dto.response;

import preonboarding.market.member.entity.Member;
import preonboarding.market.order.entity.Orders;
import preonboarding.market.product.entity.Product;

public record ProductDetailResponseDto(
        String productName,
        Long price,
        Product.ProductStatus productStatus,
        String memberName,
        Orders.OrderStatus orderStatus

) { public ProductDetailResponseDto(Product product, Member member, Orders orders){
    this(product.getName(), product.getPrice(), product.getStatus(), member.getNickname(), orders.getStatus());
}
}
