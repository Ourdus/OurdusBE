package com.ourdus.protoourdus.product.repository;

import com.ourdus.protoourdus.product.model.ProductOption;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class JpaProductOptionRepository {
    @PersistenceContext
    private final EntityManager em;

    public JpaProductOptionRepository(EntityManager em){
        this.em = em;
    }

    public void save(ProductOption option){
            em.persist(option);
    }

}
