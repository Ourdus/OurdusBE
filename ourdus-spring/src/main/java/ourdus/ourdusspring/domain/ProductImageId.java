package ourdus.ourdusspring.domain;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class ProductImageId implements Serializable {
    @EqualsAndHashCode.Include
    @Id
    private Long product;

    @EqualsAndHashCode.Include
    @Id
    private Long id;
}
