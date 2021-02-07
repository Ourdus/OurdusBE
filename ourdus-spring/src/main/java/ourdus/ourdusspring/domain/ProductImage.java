package ourdus.ourdusspring.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name="PRODUCT_IMAGE")
@Getter
@NoArgsConstructor
public class ProductImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="OPTION_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    @Lob
    @Column(name="image_file")
    private Blob imageFile;
}
