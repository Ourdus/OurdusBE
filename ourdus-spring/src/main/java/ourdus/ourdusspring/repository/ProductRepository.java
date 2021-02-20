package ourdus.ourdusspring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product save(Product product);
    Page<Product> findAll(Pageable pageable);
    Optional<Product> findById(Long id);
    Optional<List<Product>> findAllByCategoryId(Long id);
}

