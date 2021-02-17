package ourdus.ourdusspring.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import ourdus.ourdusspring.dto.PromotionDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name="Promotion")
@Getter
public class Promotion {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PROMOTION_ID")
    private Long id;

    @Column(name = "PROMOTION_NAME")
    private String name;

    @Column(name = "PROMOTION_DESCRIPTION")
    private String description;

    @Column(name = "PROMOTION_START_DATE")
    @CreationTimestamp
    private LocalDateTime start_date;

    @Column(name = "PROMOTION_END_DATE")
    private LocalDateTime end_date;

    @Column(name = "PROMOTION_IMG")
    private String image;


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStart_date(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(LocalDateTime end_date) {
        this.end_date = end_date;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @OneToMany(orphanRemoval=true, mappedBy = "promotion") //promotion_product와 one to many 관계
    private List<PromotionProduct> promotionList = new ArrayList<PromotionProduct>();

    //연관관계 메소드
    public void addPromotionProduct(PromotionProduct promotionProduct){
        promotionList.add(promotionProduct);
        promotionProduct.setPromotion(this);
    }

    @Builder(builderMethodName = "createBuilder", builderClassName = "createBuilder")
    public Promotion(String name, String description, LocalDateTime start_date,LocalDateTime end_date,String image) {
        this.name=name;
        this.description=description;
        this.start_date=start_date;
        this.end_date=end_date;
        this.image=image;
    }

    public Promotion (PromotionDTO promotiondto){
        this.id=promotiondto.getId();
        this.description=promotiondto.getDescription();
        this.name= promotiondto.getName();
        this.start_date=promotiondto.getStart_date();
        this.end_date=promotiondto.getEnd_date();
        this.image= promotiondto.getImage();
    }

//    @Builder(builderMethodName = "defaultBuilder", builderClassName = "defaultBuilder")
//    public Promotion(Long id,String name, String description,String image) {
//        this.id = id;
//        this.name=name;
//        this.description=description;
//        this.image=image;
//    }
}
