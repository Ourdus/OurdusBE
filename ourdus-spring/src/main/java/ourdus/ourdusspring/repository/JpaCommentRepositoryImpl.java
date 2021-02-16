package ourdus.ourdusspring.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaCommentRepositoryImpl implements JpaCommentRepository{

    @PersistenceContext
    private final EntityManager em;

    public JpaCommentRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void delete(Long commentId) {
        em.createQuery("delete from Comment p where p.id = ?1")
                .setParameter(1, commentId)
                .executeUpdate();
    }
}
