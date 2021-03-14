package ourdus.ourdusspring.repository.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.product.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Product save(Product product);
    Page<Product> findAll(Pageable pageable);
    List<Product> findAllByCategoryId(Long id);
}

