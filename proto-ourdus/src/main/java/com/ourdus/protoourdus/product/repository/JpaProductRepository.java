package com.ourdus.protoourdus.product.repository;

import com.ourdus.protoourdus.product.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaProductRepository implements ProductRepository{

    @PersistenceContext
    private final EntityManager em;

    public JpaProductRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Product save(Product product) {
        em.persist(product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long productId) {
        Product product = em.find(Product.class, productId);
        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery("select m from Product m", Product.class)
                .getResultList();
    }
}
