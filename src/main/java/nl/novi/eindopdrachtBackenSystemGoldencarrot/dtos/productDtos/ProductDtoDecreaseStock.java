package nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.productDtos;

import jakarta.validation.constraints.NotNull;

public class ProductDtoDecreaseStock {

    @NotNull
    public Integer reduceFromStock;

}
