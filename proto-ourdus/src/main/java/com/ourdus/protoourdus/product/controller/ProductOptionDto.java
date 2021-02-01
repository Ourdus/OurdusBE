package com.ourdus.protoourdus.product.controller;

public class ProductOptionDto {
    private String OptionName;
    private int OptionPrice;

    public String getOptionName() {
        return OptionName;
    }

    public void setOptionName(String optionName) {
        OptionName = optionName;
    }

    public int getOptionPrice() {
        return OptionPrice;
    }

    public void setOptionPrice(int optionPrice) {
        OptionPrice = optionPrice;
    }

    public ProductOptionDto(String optionName, int optionPrice) {
        OptionName = optionName;
        OptionPrice = optionPrice;
    }

    public ProductOptionDto() {
    }
}
