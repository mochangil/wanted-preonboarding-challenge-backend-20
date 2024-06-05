//package preonboarding.market.product.dto.response;
//
//import preonboarding.market.product.entity.Product;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public record ProductListResponseDto(
//        List<ProductResponseDto> products
//) {
//    public ProductListResponseDto(List<Product> productList) {
//        this.products = productList.stream()
//                .map(product -> new ProductResponseDto(product.getName(),product.getPrice(),product.getStatus()))
//                .collect(Collectors.toList());
//    }
//}
