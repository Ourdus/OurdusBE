package ourdus.ourdusspring.domain.promotion;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ourdus.ourdusspring.domain.product.Product;
import ourdus.ourdusspring.dto.promotion.PromotionProductDTO;

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

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="PROMOTION_ID")
    private Promotion promotion;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="PRODUCT_ID")
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
