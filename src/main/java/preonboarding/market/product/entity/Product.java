package preonboarding.market.product.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import preonboarding.market.common.entity.BaseEntity;
import preonboarding.market.member.entity.Member;
import preonboarding.market.product.dto.request.ProductSaveRequestDto;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Product extends BaseEntity {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long price;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", nullable = false)
    private Member member;

    public void setMember(Member member){
        if(this.member != null){
            this.member.getProducts().remove(this);
        }
        this.member = member;
        member.getProducts().add(this);
    }

    //
    public static Product from(ProductSaveRequestDto productSaveRequestDto, Member member){
        return Product.builder()
                .name(productSaveRequestDto.name())
                .price(productSaveRequestDto.price())
                .status(ProductStatus.SALE)
                .member(member)
                .build();
    }


    public enum ProductStatus{
        SALE("판매중"),
        RESERVED("예약중"),
        SOLD_OUT("완료"),
        ;
        private final String status;

        ProductStatus(String status){
            this.status = status;
        }

        public String getStatus() {
            return status;
        }


    }


}
