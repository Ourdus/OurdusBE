package hwa.hellospring.repository;

import javax.persistence.EntityManager;

public class JpaProductRepository implements ProductRepository {

    private final EntityManager em;//db랑 통신하고 관리해주는 역할

    public JpaProductRepository (EntityManager em)
    {
        this.em=em;
    }




}
