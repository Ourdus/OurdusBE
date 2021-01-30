package ourdus.ourdusspring.domain;

import javax.persistence.*;

@Entity
@Table(name="PRODUCT_OPTION")
public class Product_Option {
    @Id @GeneratedValue
    @Column(name="OPTION_ID")
    private Long id;

    @Column(name="OPTION_NAME")
    private String name;
    @Column(name="OPTION_PRICE")
    private int price;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
