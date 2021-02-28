package ourdus.ourdusspring.domain.product;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="PRODUCT_IMAGE")
@Getter
@NoArgsConstructor
public class ProductImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="IMAGE_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    @Column(name="image")
    private String image;

    public void setProduct(Product product) {
        if(this.product != null){
            this.product.getImageList().remove(this);
        }
        this.product = product;
        product.getImageList().add(this);
    }
}
