package preonboarding.market.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import preonboarding.market.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
