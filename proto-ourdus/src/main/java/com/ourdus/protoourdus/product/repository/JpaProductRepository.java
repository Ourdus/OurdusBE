package com.ourdus.protoourdus.product.repository;

import com.ourdus.protoourdus.product.model.Product;
import com.ourdus.protoourdus.product.model.ProductCategory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        if(product.getProductId() == null){
            em.persist(product);
        } else {
            em.merge(product);
        }
        return product;
    }

    @Override
    public Optional<Product> findById(Long productId) {
        Product product = em.find(Product.class, productId);
        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    @Override
    public void deleteById(Long productId) {
        em.createQuery("DELETE FROM Product p WHERE p.productId =: id")
                .setParameter("id", productId)
                .executeUpdate();
    }

    @Override
    public ProductCategory findByCategoryId(Long CategoryId) {
        return em.find(ProductCategory.class, CategoryId);
    }

    @Transactional
    @Override
    public void saveCategory(ProductCategory category) {
        em.persist(category);
    }
}
