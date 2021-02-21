package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.ProductParentOption;

import java.util.List;

public interface ProductParentOptionRepository extends JpaRepository<ProductParentOption, Long> {
    List<ProductParentOption> findAllByProductId(Long productId);
}
