package ourdus.ourdusspring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface PagingAndSortingRepository <Product,id extends Serializable> extends CrudRepository<Product,id> {

    Iterable <Product> findAll (Sort sort);
    Page<Product> findAll (Pageable pageable);

}
