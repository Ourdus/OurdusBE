package ourdus.ourdusspring.repository.promotion;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.promotion.Promotion;

import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion,Long>{
    List<Promotion> findAll();
    Promotion save(Promotion promotion);
    void deleteById(Long promotionId);
}

