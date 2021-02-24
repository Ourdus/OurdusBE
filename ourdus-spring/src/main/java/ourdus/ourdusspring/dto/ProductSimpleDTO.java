package ourdus.ourdusspring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ourdus.ourdusspring.domain.Product;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSimpleDTO {

    private Long id;
    private String name;
    private int rate;
    private Long categoryId;
    private String categoryName;
    private Long authorId;
    private String authorName;
    private String mainImage;

    public ProductSimpleDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.rate = product.getRate();
        this.categoryId = product.getCategory().getId();
        this.categoryName = product.getCategory().getName();
        this.authorId = product.getAuthor().getId();
        this.authorName = product.getAuthor().getName();
        if(product.getImageList().size() > 0)
            this.mainImage = product.getImageList().get(0).getImage();
    }
}
