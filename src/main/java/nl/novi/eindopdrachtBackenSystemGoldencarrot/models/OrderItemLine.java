package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;


import jakarta.persistence.*;

@Entity
@Table(name = "order_item_lines")
//@Table(name = "orderItemLines")
public class OrderItemLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalPrice;
    private int quantity;
    //private String productName;
    //private int quantity;
    //private double productPriceInEur;
    // private String shortDescriptionProduct;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double calculateTotalPrice(){
        return product.getPriceInEur() * quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


//    public double getProductPriceInEur() {
//        return productPriceInEur;
//    }
//
//    public void setProductPriceInEur(double productPrice) {
//        this.productPriceInEur = productPrice;
//    }
//
//    public String getShortDescriptionProduct() {
//        return shortDescriptionProduct;
//    }
//
//    public void setShortDescriptionProduct(String shortDescriptionProduct) {
//        this.shortDescriptionProduct = shortDescriptionProduct;
//    }

//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productNAme) {
//        this.productName = productNAme;
//    }

}
