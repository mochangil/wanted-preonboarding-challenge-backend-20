package preonboarding.market.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import preonboarding.market.member.entity.Member;
import preonboarding.market.order.entity.Orders;
import preonboarding.market.product.entity.Product;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders,Long> {
    Optional<Orders> findOrdersByProductId(Long productId);

    @Query("select o, p.name from Orders o join Product p on o.product.id = p.id where o.member.id = :memberId and o.product.id = :productId")
    Optional<Orders> findOrderHistory(Long memberId, Long productId);
}
