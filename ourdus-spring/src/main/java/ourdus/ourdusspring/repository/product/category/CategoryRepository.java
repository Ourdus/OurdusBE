package ourdus.ourdusspring.repository.product.category;

import org.springframework.data.jpa.repository.JpaRepository;
import ourdus.ourdusspring.domain.product.category.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long>  {

    Optional<Category> findById(Long id);
    List <Category> findAll();
}
