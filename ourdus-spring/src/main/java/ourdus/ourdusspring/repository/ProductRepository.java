package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAll();
    Product findOneById(Long id);
    List<Product> findAllByCategoryId(Long id);
}
