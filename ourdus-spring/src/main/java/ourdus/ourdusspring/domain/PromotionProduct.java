//package ourdus.ourdusspring.domain;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//
//@Entity
//@NoArgsConstructor
//@Table(name="PROMOTION_PRODUCT")
//@Getter
//public class PromotionProduct {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="PROMOTION_PRODUCT_ID" )
//    private Long Id;
//
//    @ManyToOne
//    @JoinColumn(name="PROMOTION_ID",insertable = false,updatable = false)
//    private Promotion promotion;
//
////    @ManyToOne
////    @JoinColumn(name="PRODUCT_ID",insertable = false,updatable = false)
////    private Product product;
//
//    public Long getId() {
//        return Id;
//    }
//
//    public void setId(Long id) {
//        Id = id;
//    }
//
//    public Promotion getPromotion() {
//        return promotion;
//    }
//
//    public void setPromotion(Promotion promotion) {
//        this.promotion = promotion;
//    }
//
////    public Product getProduct() {
////        return product;
////    }
////
////    public void setProduct(Product product) {
////        this.product = product;
////    }
//}
