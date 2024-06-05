package nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderItemLineDtos.OrderItemLineDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDtoUpdate {

    @NotEmpty
    @Size(min = 1)
    public List<OrderItemLineDto> products = new ArrayList<>();
}
