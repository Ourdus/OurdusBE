package ourdus.ourdusspring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ourdus.ourdusspring.dto.PromotionProductDTO;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Table(name="PROMOTION_PRODUCT")
@Getter
public class PromotionProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PROMOTION_PRODUCT_ID" )
    private Long id;

    @ManyToOne
    @JoinColumn(name="PROMOTION_ID",insertable = false,updatable = false)
    private Promotion promotion;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID",insertable = false,updatable = false)
    private Product product;



    public void setId(Long id) {
        id = id;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Product getProduct() {
        return product;
   }

   public void setProduct(Product product) {
        this.product = product;
    }


    public PromotionProduct (PromotionProductDTO promotionProductdto)
    {
        this.id=promotionProductdto.getId();
        this.promotion=promotionProductdto.getPromotion();
        this.product=promotionProductdto.getProduct();
    }

    @Builder
    public PromotionProduct( Promotion promotion, Product product ) {
        this.promotion=promotion;
        this.product=product;
    }
}
