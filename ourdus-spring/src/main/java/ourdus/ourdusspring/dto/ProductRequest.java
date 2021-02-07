package ourdus.ourdusspring.dto;

import java.util.List;

public class ProductRequest {

    private String name;
    private int price;
    private Long categoryId;
    private int optionNum;
   // private List<Product_OptionDTO> options;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public int getOptionNum() {
        return optionNum;
    }

    public void setOptionNum(int optionNum) {
        this.optionNum = optionNum;
    }

//    public List<Product_OptionDTO> getOptions() {
//        return options;
//    }

//    public void setOptions(List<Product_OptionDTO> options) {
//        this.options = options;
//    }
}
