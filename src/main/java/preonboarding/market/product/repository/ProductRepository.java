package preonboarding.market.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import preonboarding.market.product.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByMemberId(Long MemberId);
}
