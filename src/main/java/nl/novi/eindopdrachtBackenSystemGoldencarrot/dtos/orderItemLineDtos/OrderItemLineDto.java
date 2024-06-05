package nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderItemLineDtos;


import jakarta.validation.constraints.NotBlank;

public class OrderItemLineDto {

    public Long id;
    @NotBlank
    public String productName;

    public String shortDescriptionProduct;

    public double productPriceInEur;
    @NotBlank
    public int quantity;

    public double totalPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getShortDescriptionProduct() {
        return shortDescriptionProduct;
    }

    public void setShortDescriptionProduct(String shortDescriptionProduct) {
        this.shortDescriptionProduct = shortDescriptionProduct;
    }

    public double getProductPriceInEur() {
        return productPriceInEur;
    }

    public void setProductPriceInEur(double productPriceInEur) {
        this.productPriceInEur = productPriceInEur;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
