package preonboarding.market.product.dto.response;

import preonboarding.market.product.entity.Product;



public record ProductResponseDto(
        String name,
        Long price,
        Product.ProductStatus status
) {
    public ProductResponseDto(Product product) {

        this(product.getName(),product.getPrice(),product.getStatus());
    }
}
