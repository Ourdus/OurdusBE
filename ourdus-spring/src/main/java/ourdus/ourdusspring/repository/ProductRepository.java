package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Optional<List<Product>> findAllByCategoryId(Long id);
}
