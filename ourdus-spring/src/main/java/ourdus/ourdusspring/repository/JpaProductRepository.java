package ourdus.ourdusspring.repository;
import ourdus.ourdusspring.domain.Product;

public interface JpaProductRepository {
    void delete(Long productId);
    void update(Long product_id, Product product, Long categoryId);
}
