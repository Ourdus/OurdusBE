package hwa.hellospring.domain;

import javax.persistence.*;
@Entity
@Table(name="PRODUCT_CATEGORY")
public class Category {

    @Id
    @GeneratedValue
    @Column(name="CATEGORY_ID")
    private int category_id;

    @Column(name="CATEGORY_NAME")
    private String category_name;


    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

}
