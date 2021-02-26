package ourdus.ourdusspring.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="PRODUCT_CATEGORY")
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CATEGORY_ID")
    private Long id;

    @Column(name="CATEGORY_NAME")
    private String name;

    @Column(name="CATEGORY_IMAGE")
    private String image;

   /* @OneToMany(mappedBy = "category")
    private List<Product> products;*/

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }
}
