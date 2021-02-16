package ourdus.ourdusspring.dto;

import ourdus.ourdusspring.domain.Product;
import ourdus.ourdusspring.domain.Promotion;
import ourdus.ourdusspring.domain.PromotionProduct;

import javax.persistence.*;

public class PromotionProductDTO {


    private Long id;
    private Promotion promotion;
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public PromotionProductDTO(PromotionProduct promotionProduct)
    {
        this.id=promotionProduct.getId();
        this.promotion=promotionProduct.getPromotion();
        this.product=promotionProduct.getProduct();
    }
}
