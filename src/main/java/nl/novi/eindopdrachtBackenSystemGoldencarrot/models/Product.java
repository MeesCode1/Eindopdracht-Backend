package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private Double priceInEur;
    private String packedPerUnit;
    private Integer inStock;
    private String shortDescription;
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItemLine> orderItemLines = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPriceInEur() {
        return priceInEur;
    }

    public void setPriceInEur(Double price) {
        this.priceInEur = price;
    }

    public String getPackedPerUnit() {
        return packedPerUnit;
    }

    public void setPackedPerUnit(String packedPerUnit) {
        this.packedPerUnit = packedPerUnit;
    }

    public int getInStock() {
        return inStock;
    }

    public void lowerInStockForIncomingOrder(int inStock) {
        this.inStock -= inStock;
    }

    public void restoreInStockForChangedOrder(int inStock){
        this.inStock += inStock;
    }
    public void setInStock(int inStock){
        this.inStock = inStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
}

