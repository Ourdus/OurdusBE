package ourdus.ourdusspring.repository.product.option;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.product.option.ProductChildOption;

import java.util.List;

public interface ProductChildOptionRepository extends JpaRepository<ProductChildOption, Long> {
    List<ProductChildOption> findAllByProductId(Long productId);
}
