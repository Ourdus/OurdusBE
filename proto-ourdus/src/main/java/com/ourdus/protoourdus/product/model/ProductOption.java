package com.ourdus.protoourdus.product.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name="product_option")
@Getter
@Setter //TODO Setter 수정
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long optionId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "option_name")
    private String optionName;

    @Column(name = "option_price")
    @ColumnDefault("0")
    private int optionPrice;

    public ProductOption(Product product){
        this.product = product;
    }

    public ProductOption() {}
}
