package ourdus.ourdusspring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name="PRODUCT_PARENT_OPTION")
@Getter
@NoArgsConstructor
public class ProductParentOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PRODUCT_PARENT_OPTION_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    @Column(name="OPTION_NAME")
    private String name;

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public ProductParentOption(Product product, String name) {
        this.product = product;
        this.name = name;
    }

    @Builder(builderClassName = "defaultBuilder", builderMethodName = "defaultBuilder")
    public ProductParentOption(Long id, Product product, String name) {
        this.id = id;
        this.product = product;
        this.name = name;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toStringExclude(this, new String[] {"product"});
    }
}
