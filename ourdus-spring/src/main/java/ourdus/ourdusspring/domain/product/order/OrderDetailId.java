package ourdus.ourdusspring.domain.product.order;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class OrderDetailId implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    private Long order;

    @EqualsAndHashCode.Include
    @Id
    private Long id;
}
