package ourdus.ourdusspring.dto.product.order;

import ourdus.ourdusspring.domain.product.order.Cart;

public class CartDTO {

    private Long id;
    private Long authorId;
    private String authorName;
    private Long productId;
    private String productName;
    private String optionInfo;
    private int productNum;
    private int productDetailPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOptionInfo() {
        return optionInfo;
    }

    public void setOptionInfo(String optionInfo) {
        this.optionInfo = optionInfo;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public int getProductDetailPrice() {
        return productDetailPrice;
    }

    public void setProductDetailPrice(int productDetailPrice) {
        this.productDetailPrice = productDetailPrice;
    }

    public CartDTO(Cart cart) {
        this.id = cart.getId();
        this.authorId = cart.getAuthorId();
        this.authorName = cart.getProduct().getAuthor().getName();
        this.productId = cart.getProduct().getId();
        this.productName = cart.getProduct().getName();
        this.optionInfo = cart.getOptionInfo();
        this.productNum = cart.getProductNum();
        this.productDetailPrice = cart.getPrice();
    }

    public CartDTO() {
    }

    public CartDTO(Long id, Long authorId, String authorName, Long productId, String productName, String optionInfo, int productNum, int productDetailPrice) {
        this.id = id;
        this.authorId = authorId;
        this.authorName = authorName;
        this.productId = productId;
        this.productName = productName;
        this.optionInfo = optionInfo;
        this.productNum = productNum;
        this.productDetailPrice = productDetailPrice;
    }
}
