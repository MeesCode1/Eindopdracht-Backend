package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDtoDecreaseStock;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDtoIncreaseStock;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos.ProductDtoUpdate;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.BindingValidator;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProductDto> showProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{productname}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String productname) {
        ProductDto pdto = service.getProductByName(productname);

        return ResponseEntity.ok(pdto);
    }

    @GetMapping("/category/{category}")
    public List<ProductDto> getProductsByCategory(@PathVariable String category) {

        return service.getProductsByCategory(category);
    }

    @PostMapping
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductDto pdto,
                                                BindingResult br) {

        String fieldErrors = BindingValidator.validateInput(br);
        if (fieldErrors != null) {
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }
        pdto = service.createProduct(pdto);

        URI uri = URI.create(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/" + pdto.id).toUriString());

        return ResponseEntity.created(uri).body(pdto);
    }

    @PutMapping("/{productname}")
    public ResponseEntity<Object> updateProduct(@PathVariable String productname,
                                                @RequestBody ProductDtoUpdate pdto) {

        ProductDto updatedProductDto = service.updateProduct(productname, pdto);
        return ResponseEntity.ok(updatedProductDto);
    }

    @PutMapping("/increasestock/{productname}")
    public ResponseEntity<Object> increaseProductStock(@PathVariable String productname,
                                                       @Valid @RequestBody ProductDtoIncreaseStock pdto,
                                                           BindingResult br) {

        String fieldErrors = BindingValidator.validateInput(br);
        if (fieldErrors != null) {
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }
        ProductDto updatedProductDto = service.increaseProductStock(productname, pdto);
        return ResponseEntity.ok(updatedProductDto);
    }

    @PutMapping("/decreasestock/{productname}")
    public ResponseEntity<Object> decreaseProductStock(@PathVariable String productname,
                                                       @Valid @RequestBody ProductDtoDecreaseStock pdto,
                                                       BindingResult br) {

        String fieldErrors = BindingValidator.validateInput(br);
        if (fieldErrors != null) {
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }
        ProductDto updatedProductDto = service.decreaseProductStock(productname, pdto);
        return ResponseEntity.ok(updatedProductDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        String deletedProduct = service.deleteProduct(id);
        return ResponseEntity.ok("Product: \"" + deletedProduct + "\"deleted from database");
    }

}
