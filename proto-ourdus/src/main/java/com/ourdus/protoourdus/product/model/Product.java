package com.ourdus.protoourdus.product.model;

import com.ourdus.protoourdus.user.model.User;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name="author_id")
    private User user;

    @ManyToOne
    @Column(name= "category_id")
    private ProductCategory productCategory;

    @Column(name = "product_name")
    private String productName;

    @Column(name= "product_price")
    @ColumnDefault("0")
    private int productPrice;

    @Column(name= "product_rate")
    @ColumnDefault("0")
    private int productRate;

    @Column(name= "product_review_num")
    @ColumnDefault("0")
    private int productReviewNum;

    @Column(name = "product_hit")
    @ColumnDefault("0")
    private int productHit;

    @Column(name = "product_purchase")
    @ColumnDefault("0")
    private int productPurchase;

    @Column(name = "product_option_num")
    @ColumnDefault("0")
    private int productOptionNum;

}
