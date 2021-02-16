package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.PromotionProduct;

public interface PromotionProductRepository extends JpaRepository<PromotionProduct,Long> {

    PromotionProduct save(PromotionProduct promotionProduct);

}
