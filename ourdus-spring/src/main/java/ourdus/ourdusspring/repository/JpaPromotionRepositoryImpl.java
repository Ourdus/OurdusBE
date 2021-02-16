package ourdus.ourdusspring.repository;

import ourdus.ourdusspring.domain.Product;
import ourdus.ourdusspring.domain.Promotion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaPromotionRepositoryImpl implements JpaPromotionRepository{

    @PersistenceContext
    private final EntityManager em;

    public JpaPromotionRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void delete(Long promotion_id) {
        em.createQuery("delete from Promotion p where p.id = ?1")
                .setParameter(1, promotion_id)
                .executeUpdate();
    }

    @Override
    public void update(Long promotion_id, Promotion promotion) {
        em.createQuery("update Promotion p set promotion_name=?1, promotion_description=?2, promotion_img=?3  where p.id = ?0")
                .setParameter(0, promotion_id)
                .setParameter(1, promotion.getName())
                .setParameter(2, promotion.getDescription())
                .setParameter(3, promotion.getImage())
                .executeUpdate();
    }
}
