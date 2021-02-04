package ourdus.ourdusspring.repository;

import ourdus.ourdusspring.domain.Category;
import ourdus.ourdusspring.domain.Product;

import java.util.Optional;

public interface CategoryRepository {

    Optional<Category> findOneById(Long id);
}
