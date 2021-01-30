package ourdus.ourdusspring.dto;

import ourdus.ourdusspring.domain.Product;
import ourdus.ourdusspring.domain.Product_Option;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Product_OptionDTO {
    private Long id;
    private String name;
    private int price;
    private Long productId;

    public Product_OptionDTO(Product_Option product_option) {
        this.id = product_option.getId();
        this.name = product_option.getName();
        this.price = product_option.getPrice();
        this.productId = product_option.getProduct().getId();
    }

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
