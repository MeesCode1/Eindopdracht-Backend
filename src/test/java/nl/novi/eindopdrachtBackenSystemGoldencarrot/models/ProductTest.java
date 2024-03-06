package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUpProduct() {
        this.product = new Product();
    }

//    @Test
//    void getId() {
//    }
//    @Test
//    void shouldSaveAndReturnCorrectId() {
//        Long id = 1L;
//        product.setId(id);
//        assertEquals(id, product.getId());
//    }

    @Test
    void shouldSaveAndReturnCorrectName() {
        String name = "biefstuk";
        product.setName(name);
        assertEquals(name, product.getName());
    }

    @Test
    void shouldSaveAndReturnCorrectCategory() {
        String category = "meat";
        product.setCategory(category);
        assertEquals(category, product.getCategory());
    }

    @Test
    void shouldSaveAndReturnCorrectPriceInEur() {
        Double price = 50.0;
        product.setPriceInEur(price);
        assertEquals(price, product.getPriceInEur());
    }

    @Test
    void shouldSaveAndReturnCorrectPackedPerUnit() {
        String packedPerUnit = "1 kg";
        product.setPackedPerUnit(packedPerUnit);
        assertEquals(packedPerUnit, product.getPackedPerUnit());
    }

    @Test
    void shouldSaveAndReturnCorrectInStock() {
        int inStock = 100;
        product.setInStock(inStock);
        assertEquals(inStock, product.getInStock());
    }

    @Test
    void shouldLowerInStockForIncomingOrder() {
        int actualInStock = 100;
        int incomingOrderQuantity = 20;

        product.setInStock(actualInStock);
        product.lowerInStockForIncomingOrder(incomingOrderQuantity);

        assertEquals(actualInStock - incomingOrderQuantity, product.getInStock());
    }

    @Test
    void shouldRestoreInStockForChangedOrder() {
        int actualInStock = 100;
        int changedOrderQuantity = 30;

        product.setInStock(actualInStock);
        product.restoreInStockForChangedOrder(changedOrderQuantity);

        assertEquals(actualInStock + changedOrderQuantity, product.getInStock());
    }

    @Test
    void shouldSaveAndReturnCorrectDescription() {
        String description = "Product Description";
        product.setDescription(description);
        assertEquals(description, product.getDescription());
    }

    @Test
    void shouldSaveAndReturnCorrectShortDescription() {
        String shortDescription = "Short Description";
        product.setShortDescription(shortDescription);
        assertEquals(shortDescription, product.getShortDescription());
    }
}
