package preonboarding.product;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import preonboarding.market.member.entity.Member;
import preonboarding.market.order.entity.Orders;
import preonboarding.market.order.repository.OrderRepository;
import preonboarding.market.product.dto.response.ProductResponseDto;
import preonboarding.market.product.entity.Product;
import preonboarding.market.product.repository.ProductRepository;
import preonboarding.market.product.service.ProductService;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private ProductService productService;

    private Product product;
    private Orders orders;
    private Member member;

    @BeforeEach
    void init(){
        member = createMember();
        ReflectionTestUtils.setField(member,"id",1L);
        product = createProduct(member);
        ReflectionTestUtils.setField(product,"id",1L);
        orders = createOrders(product,member);
        ReflectionTestUtils.setField(orders,"id",1L);
    }

    @Test
    @DisplayName("성공 - 인증된 사용자가 제품 상세 정보 조회 성공, 거래내역 존재")
    void gettingProductSuccessWithoutAuth(){
        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        ProductResponseDto result = productService.getProduct(1L);
        assertThat(result.price()).isEqualTo(1000);
    }


    private Member createMember(){
        return Member.builder()
                .nickname("Mo")
                .password("PW1234")
                .email("moEmail")
                .build();
    }

    private Product createProduct(Member member){
        return Product.builder()
                .name("ProductName")
                .price(1000L)
                .status(Product.ProductStatus.SALE)
                .member(member)
                .build();
    }

    private Orders createOrders(Product product, Member member){
        return Orders.builder()
                .member(member)
                .product(product)
                .build();
    }
}
