package preonboarding.market.product.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import preonboarding.market.member.entity.Member;
import preonboarding.market.member.repository.MemberRepository;
import preonboarding.market.order.entity.Orders;
import preonboarding.market.order.repository.OrderRepository;
import preonboarding.market.product.dto.request.ProductDetailRequestDto;
import preonboarding.market.product.dto.request.ProductSaveRequestDto;
import preonboarding.market.product.dto.response.ProductDetailResponseDto;
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
    private final OrderRepository orderRepository;

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

    //판매자와 구매자가 제품의 상세정보를 조회해 당사자간 거래내역 확인
    public ProductDetailResponseDto getProductDetail(ProductDetailRequestDto productDetailRequestDto){
        //Product.memberId와 같으면 구매중인 모든 사용자와의 리스트
        //Order MemberId와 같으면 해당 판매자와의 거래내역 리턴
        Long memberId = 1L;
        Member foundMember = memberRepository.findById(memberId).orElseThrow(()->{
            throw new IllegalArgumentException("해당 멤버가 존재하지 않습니다");
        });
        Product foundProduct = productRepository.findById(productDetailRequestDto.productId()).orElseThrow(()->{
            throw new IllegalArgumentException("해당 제품이 존재하지 않습니다");
        });
        Orders foundOrder = orderRepository.findOrdersByProductId(productDetailRequestDto.productId()).orElseThrow(()->{
            throw new IllegalArgumentException("해당 주문이 존재하지 않습니다");
        });


        return new ProductDetailResponseDto(foundProduct.getName(),foundProduct.getPrice(),foundProduct.getStatus(),foundMember.getNickname(),foundOrder.getStatus());
//        if (foundMember.getId().equals(foundProduct.getMember().getId())){
//
//        }
    }


}
