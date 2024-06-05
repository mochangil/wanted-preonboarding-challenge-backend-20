package preonboarding.market.product.dto.request;


import preonboarding.market.product.entity.Product;


public record ProductSaveRequestDto (
    String name,
    Long price

    ){
    public ProductSaveRequestDto(Product product){
        this(product.getName(),product.getPrice());
    }
}
