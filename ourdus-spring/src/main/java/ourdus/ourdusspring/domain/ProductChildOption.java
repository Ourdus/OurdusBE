package ourdus.ourdusspring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import ourdus.ourdusspring.dto.ProductChildOptionRequest;

import javax.persistence.*;

@Entity
@Table(name="PRODUCT_CHILD_OPTION")
@Getter
@NoArgsConstructor
public class ProductChildOption {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PRODUCT_CHILD_OPTION_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="PRODUCT_PARENT_OPTION_ID")
    private ProductParentOption pOption;

    @Column(name="PRODUCT_ID")
    private Long productId;

    @Column(name="OPTION_NAME")
    private String name;
    @Column(name="OPTION_PRICE")
    private int price;

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public ProductChildOption(ProductParentOption pOption, ProductChildOptionRequest childOptionRequest) {
        this.pOption = pOption;
        this.productId = pOption.getProduct().getId();
        this.name = childOptionRequest.getName();
        this.price = childOptionRequest.getPrice();
    }
    @Builder(builderClassName = "defaultBuilder", builderMethodName = "defaultBuilder")
    public ProductChildOption(Long id, ProductParentOption pOption, Long productId, String name, int price) {
        this.id = id;
        this.pOption = pOption;
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toStringExclude(this, new String[] {"pOption"});
    }
}
