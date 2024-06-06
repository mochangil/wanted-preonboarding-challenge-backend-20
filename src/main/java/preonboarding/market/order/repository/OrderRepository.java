package preonboarding.market.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import preonboarding.market.member.entity.Member;
import preonboarding.market.order.entity.Orders;
import preonboarding.market.product.entity.Product;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders,Long> {
}
