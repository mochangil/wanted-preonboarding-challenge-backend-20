package preonboarding.market.product.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import preonboarding.market.product.dto.request.ProductDetailRequestDto;
import preonboarding.market.product.dto.request.ProductSaveRequestDto;
import preonboarding.market.product.dto.response.ProductDetailResponseDto;
import preonboarding.market.product.dto.response.ProductResponseDto;
import preonboarding.market.product.service.ProductService;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    private ResponseEntity<List<ProductResponseDto>> getProductList(){
        return ResponseEntity.ok()
                .body(productService.getProductList());
    }

    @PostMapping
    private ResponseEntity<ProductResponseDto> getProduct(@RequestBody Map<String, Long> body) {

        return ResponseEntity.ok().
                body(productService.getProduct(body.get("id")));
    }

    @PostMapping("/save")
    public void saveProduct(@RequestBody ProductSaveRequestDto productSaveRequestDto){
        Long memberId = 1L;
        productService.saveProduct(memberId,productSaveRequestDto);

    }

    @PostMapping("/detail")
    public ResponseEntity<ProductDetailResponseDto> getProductDetail(@RequestBody ProductDetailRequestDto productDetailRequestDto){
        Long memberId = 1L;
        return ResponseEntity.ok()
                .body(productService.getProductDetail(productDetailRequestDto));
    }


}
