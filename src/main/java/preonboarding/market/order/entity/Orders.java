package preonboarding.market.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.query.Order;
import preonboarding.market.common.entity.BaseEntity;
import preonboarding.market.member.entity.Member;
import preonboarding.market.order.dto.request.OrderSaveRequestDto;
import preonboarding.market.product.entity.Product;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Orders extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    //단방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable=false)
    private Member member;

    //단방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",  nullable = false)
    private Product product;


    public static Orders from(Member member, Product product){
        return Orders.builder()
                .member(member)
                .product(product)
                .status(OrderStatus.BUYING)
                .build();
    }
    public enum OrderStatus{
        BUYING("구매중"),
        CONFIRMED("구매확정"),
        ;

        private final String status;

        OrderStatus(String status) {this.status = status;}

        public String getStatus() {return status;}
    }

    //주문 상태 변환환
   public void changeStatus(OrderStatus status){
        this.status = status;
    }


}
