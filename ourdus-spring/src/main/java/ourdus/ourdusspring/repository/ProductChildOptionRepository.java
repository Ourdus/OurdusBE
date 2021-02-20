package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.ProductChildOption;

import java.util.List;

public interface ProductChildOptionRepository extends JpaRepository<ProductChildOption, Long> {
    List<ProductChildOption> findAllByProductId(Long productId);
}
