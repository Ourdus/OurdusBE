package ourdus.ourdusspring.repository.promotion;

import ourdus.ourdusspring.domain.promotion.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PromotionRepository extends JpaRepository<Promotion,Long>{
    List<Promotion> findAll();
    Optional <Promotion> findById(Long Id);
    Promotion save(Promotion promotion);
    void deleteById(Long promotionId);
}

