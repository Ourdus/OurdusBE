package ourdus.ourdusspring.domain.product;

public enum ProductOrderCategory {
    RATE("rate"),
    HIT("hit"),
    PRICE("price"),
    PURCHASE("purchase");

    private final String category;

    ProductOrderCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
