package ourdus.ourdusspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.Product;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> save(Product product);
    List<Product> findAll();
    Optional<Product> findOneById(Long id);
    Optional<List<Product>> findAllByCategoryId(Long id);
}
