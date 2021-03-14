package ourdus.ourdusspring.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private String name;
    private int price;
    private String info;
    private Long categoryId;
    private int optionNum;
   // private List<Product_OptionDTO> options;

    //    public List<Product_OptionDTO> getOptions() {
//        return options;
//    }

//    public void setOptions(List<Product_OptionDTO> options) {
//        this.options = options;
//    }
}
