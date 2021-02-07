package ourdus.ourdusspring.dto;

import ourdus.ourdusspring.domain.OrderDetail;

public class OrderDetailDTO {
    private Long id;
    private Long orderId;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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


    public OrderDetailDTO(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
        this.orderId = orderDetail.getOrder().getId();
        this.authorId = orderDetail.getAuthor().getId();
        this.authorName = orderDetail.getAuthor().getName();
        this.productId = orderDetail.getProduct().getId();
        this.productName = orderDetail.getProduct().getName();
        this.optionInfo = orderDetail.getOptionInfo();
        this.productNum = orderDetail.getProductNum();
        this.productDetailPrice = orderDetail.getPrice();
    }
}
