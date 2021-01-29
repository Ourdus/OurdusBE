package hwa.hellospring.repository;

import hwa.hellospring.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    public Product save(Product product);
    public Optional<Product> findById(int id);
    public List<Product> findAll();
    public Optional<Product> findByName(String name);
}
