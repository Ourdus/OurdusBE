package hwa.hellospring.repository;

import hwa.hellospring.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class JpaProductRepository implements ProductRepository {

    private final EntityManager em;//db랑 통신하고 관리해주는 역할

    public JpaProductRepository (EntityManager em)
    {
        this.em=em;
    }

    @Override
    public Optional<Product> save(Product product) {
        em.persist(product);
        return Optional.ofNullable(product);
    }
    @Override
    public Optional<Product> findById(int id) {
        Product product = em.find(Product.class, id);
        return Optional.ofNullable(product);
    }
    @Override
    public List<Product> findAll() {
        return em.createQuery("select p from Product p", Product.class)
                .getResultList();
    }
    @Override
    public Optional<Product> findOneById(int product_id) {//mapping이 필요없이 이미 되어있음
        List<Product> result = em.createQuery("select p from Product p where p.product_id = :product_id", Product.class)
                .setParameter("product_id", product_id)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public int delete(int product_id) {
        Query query= em.createQuery("delete from Product p where p.product_id = :product_id", Product.class);
        int result=query.setParameter("product_id", product_id).executeUpdate();
        return result;
    }
}

