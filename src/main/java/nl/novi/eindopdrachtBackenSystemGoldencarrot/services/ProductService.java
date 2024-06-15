package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDtoDecreaseStock;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDtoIncreaseStock;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDtoUpdate;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ResourceNotFoundException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Product;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.ProductRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.ModelMapperConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repos;


    public ProductService(ProductRepository repos) {
        this.repos = repos;
    }

    public ProductDto createProduct(ProductDto pdto) {

        Product p = ModelMapperConfig.mappingToEntityProduct(pdto);

        pdto = ModelMapperConfig.mappingToDtoProduct(repos.save(p));

        return pdto;
    }

    public ProductDto getProductByName(String productname) {

        Product p = repos.findByName(productname).orElseThrow(() ->
                new ResourceNotFoundException("product not found"));

        return ModelMapperConfig.mappingToDtoProduct(p);
    }

    public List<ProductDto> getProductsByCategory(String category) {

        List<Product> products = repos.findByCategory(category);

        List<ProductDto> pdtos = new ArrayList<>();
        for (Product p : products) {
            ProductDto pdto = ModelMapperConfig.mappingToDtoProduct(p);
            pdtos.add(pdto);
        }

        return pdtos;
    }

    public List<ProductDto> getAllProducts() {
        Iterable<Product> products = repos.findAll();

        List<ProductDto> pDtos = new ArrayList<>();

        for (Product p : products) {

            ProductDto pdto = ModelMapperConfig.mappingToDtoProduct(p);
            pDtos.add(pdto);
        }
        return pDtos;
    }

    public ProductDto updateProduct(String productname, ProductDtoUpdate pdto) {
        Product p = repos.findByName(productname).orElseThrow(() ->
                new ResourceNotFoundException("product not found"));

        if (pdto.name != null) {
            p.setName(pdto.name);
        }
        if (pdto.priceInEur != null) {
            p.setPriceInEur(pdto.priceInEur);
        }
        if (pdto.inStock != null) {
            p.setInStock(pdto.inStock);
        }
        if (pdto.packedPerUnit != null) {
            p.setPackedPerUnit(pdto.packedPerUnit);
        }
        if (pdto.shortDescription != null) {
            p.setShortDescription(pdto.shortDescription);
        }
        if (pdto.description != null) {
            p.setDescription(pdto.description);
        }

        Product updatedProduct = repos.save(p);

        return ModelMapperConfig.mappingToDtoProduct(updatedProduct);
    }

    public ProductDto increaseProductStock(String productname, ProductDtoIncreaseStock pdto) {
        Product p = repos.findByName(productname).orElseThrow(() ->
                new ResourceNotFoundException("Product not found"));

        p.setInStock(p.getInStock() + pdto.addToStock);

        Product updatedProduct = repos.save(p);

        return ModelMapperConfig.mappingToDtoProduct(updatedProduct);
    }

    public ProductDto decreaseProductStock(String productname, ProductDtoDecreaseStock pdto) {
        Product p = repos.findByName(productname).orElseThrow(() ->
                new ResourceNotFoundException("Product not found"));

        p.setInStock(p.getInStock() - pdto.reduceFromStock);

        Product updatedProduct = repos.save(p);

        return ModelMapperConfig.mappingToDtoProduct(updatedProduct);
    }


    public String deleteProduct(Long id) {
        Product p = repos.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("product not found"));
        String productName = p.getName();
        repos.delete(p);
        return productName;
    }


    // side methods
    public void lowerInStockNewOrder(String productName, int quantity) {

        Product p = repos.findByName(productName).orElseThrow(() ->
                new ResourceNotFoundException("product not found"));

        if (p.getInStock() < quantity) {
            throw new IllegalArgumentException("Only " + p.getInStock() + " " + p.getName() + " in stock");
        } else {
            p.lowerInStockForIncomingOrder(quantity);
            repos.save(p);
        }
    }

    public void restoreInStockForChangedOrder(String productName, int quantity) {
        Product p = repos.findByName(productName).orElseThrow(() ->
                new ResourceNotFoundException("product not found"));

        p.restoreInStockForChangedOrder(quantity);
        repos.save(p);
    }

}



