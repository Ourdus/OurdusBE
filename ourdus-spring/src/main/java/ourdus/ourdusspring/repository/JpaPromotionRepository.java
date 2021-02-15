package ourdus.ourdusspring.repository;

import ourdus.ourdusspring.domain.Product;
import ourdus.ourdusspring.domain.Promotion;

public interface JpaPromotionRepository {
    void delete(Long productId);
    void update(Long promotion_id, Promotion promotion);
}
