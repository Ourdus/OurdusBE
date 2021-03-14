package ourdus.ourdusspring.repository.product.option;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.product.option.ProductParentOption;

import java.util.List;

public interface ProductParentOptionRepository extends JpaRepository<ProductParentOption, Long> {
    List<ProductParentOption> findAllByProductId(Long productId);
}
