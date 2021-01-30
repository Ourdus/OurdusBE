package ourdus.ourdusspring.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PRODUCT_CATEGORY")
public class Category {

    @Id @GeneratedValue
    @Column(name="CATEGORY_ID")
    private Long id;

    @Column(name="CATEGORY_NAME")
    private String name;

//    @OneToMany
//    @JoinColumn(name="CATEGORY_ID")
//    private List<Product> products = new ArrayList<Product>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }
}
