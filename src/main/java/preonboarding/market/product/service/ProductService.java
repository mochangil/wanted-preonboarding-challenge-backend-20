package preonboarding.market.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preonboarding.market.member.entity.Member;
import preonboarding.market.member.repository.MemberRepository;
import preonboarding.market.product.dto.request.ProductSaveRequestDto;
import preonboarding.market.product.dto.response.ProductResponseDto;
import preonboarding.market.product.entity.Product;
import preonboarding.market.product.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    //제품 등록(only 회원)
    public void saveProduct(final Long memberId, ProductSaveRequestDto productSaveRequestDto){
        Member member = memberRepository.findById(memberId).orElseThrow(()->{
            throw new IllegalArgumentException("회원 정보 존재하지 않음.");
        });

        Product product = Product.from(productSaveRequestDto, member);
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public ProductResponseDto getProduct(final Long productId){
        Product foundProduct = productRepository.findById(productId).orElseThrow(()-> {
            throw new IllegalArgumentException("물건이 존재하지 않습니다.");

        });
        return new ProductResponseDto(foundProduct.getName(),foundProduct.getPrice(),foundProduct.getStatus());
    }

//    목록 조회(누구나)
    public List<ProductResponseDto> getProductList(){
        List<Product> foundProductList = productRepository.findAll();
        //record로 옮기고 싶은데 (ListProductResponseDto record 생성) 쉽지않다.
        //서비스 계층에서 비즈니스 로직 이외의 로직이 있어 불편
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        foundProductList.forEach((product -> productResponseDtoList.add(new ProductResponseDto(product))));
        return productResponseDtoList;
    }


}
