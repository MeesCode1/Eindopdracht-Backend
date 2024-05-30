package nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.*;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class ModelMapperConfig {

    static ModelMapper modelMapper = new ModelMapper();

    //customer
    public static Customer mappingToEntityCustomer(CustomerDto cDto) {
        return modelMapper.map(cDto, Customer.class);
    }

    public static CustomerDto mappingToDtoCustomer(Customer c) {
        return modelMapper.map(c, CustomerDto.class);
    }

    //order
    public static OrderDto mappingToDtoOrder(Order order) {

        List<OrderItemLine> orderItemLines = order.getProducts();
        List<OrderItemLineDto> ildtos = new ArrayList<>();
        for (OrderItemLine il : orderItemLines) {
            OrderItemLineDto ildto = new OrderItemLineDto();
            ildto.id = il.getId();
            ildto.productName = il.getProduct().getName();
            ildto.shortDescriptionProduct = il.getProduct().getShortDescription();
            ildto.productPriceInEur = il.getProduct().getPriceInEur();
            ildto.quantity = il.getQuantity();
            ildto.totalPrice = il.getTotalPrice();
            ildtos.add(ildto);
        }

        OrderDto odto = modelMapper.map(order, OrderDto.class);
        odto.setProducts(ildtos);
        return odto;
    }

    //product

    public static Product mappingToEntityProduct(ProductDto pDto) {
        return modelMapper.map(pDto, Product.class);
    }

    public static ProductDto mappingToDtoProduct(Product p) {
        return modelMapper.map(p, ProductDto.class);
    }

    //userEmployee

    public static UserEmployeeDto mappingToDtoUserEmployee(UserEmployee user) {

        UserEmployeeDto udto = modelMapper.map(user, UserEmployeeDto.class);
        udto.password = "secret info";

        List<String> rolenames = new ArrayList<>();

        Collection<Role> userroles = user.getRoles();
        for (Role userrole : userroles) {
            rolenames.add(userrole.getRoleName());
        }
        udto.roles = (rolenames);

        return udto;
    }

    public static UserEmployee mappingToEntityUserEmployee(UserEmployeeDto uDto) {
        return modelMapper.map(uDto, UserEmployee.class);
    }
}
