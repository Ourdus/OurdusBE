package ourdus.ourdusspring.repository;

import ourdus.ourdusspring.domain.Product;

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

    @Override
    public void update(Long product_id, Product product, Long categoryId) {
        em.createQuery("update Product p set product_name=?1, product_price=?2, product_option_num=?3 where p.id = ?0")
                .setParameter(0, product_id)
                .setParameter(1, product.getName())
                .setParameter(2, product.getPrice())
                .setParameter(3, product.getOptionNum())
                .executeUpdate();
    }

}
