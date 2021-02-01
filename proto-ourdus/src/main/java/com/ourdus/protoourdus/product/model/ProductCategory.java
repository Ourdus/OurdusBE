package com.ourdus.protoourdus.product.model;

import javax.persistence.*;

@Entity
@Table(name= "product_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "category_name")
    private String categoryName;

}
