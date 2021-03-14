package ourdus.ourdusspring.repository.promotion;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.promotion.PromotionProduct;

public interface PromotionProductRepository extends JpaRepository<PromotionProduct,Long> {

    PromotionProduct save(PromotionProduct promotionProduct);
    void deleteById(Long promotionId);
}
