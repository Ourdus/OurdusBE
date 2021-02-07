package ourdus.ourdusspring.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaProductRepositoryImpl implements JpaProductRepository{

    @PersistenceContext
    private final EntityManager em;

    public JpaProductRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void delete(Long product_id) {
        em.createQuery("delete from Product p where p.id = ?1")
                .setParameter(1, product_id)
                .executeUpdate();
    }
}
