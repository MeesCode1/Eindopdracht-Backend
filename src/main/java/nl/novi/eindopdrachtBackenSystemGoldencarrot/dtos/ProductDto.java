package nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductDto {

    public Long id;
    @NotBlank
    public String name;
    @NotBlank
    public String category;
    @NotNull
    public Double priceInEur;
    @NotBlank
    public String packedPerUnit;
    @NotNull
    public Integer inStock;
    @NotBlank
    public String shortDescription;
    @NotBlank
    public String description;

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

    public void setPriceInEur(Double priceInEur) {
        this.priceInEur = priceInEur;
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

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

