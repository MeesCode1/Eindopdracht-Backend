package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderDtos.OrderDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderItemLineDtos.OrderItemLineDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
class OrderControllerDiffblueTest {
    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderService orderService;


    /**
     * Method under test:
     * {@link OrderController#createOrder(OrderDto, BindingResult)}
     */
    @Test
    void testCreateOrder() throws Exception {
        // Arrange
        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerCompany("Customer Company");
        orderDto.setCustomerFirstName("Jane");
        orderDto.setCustomerLastName("Doe");
        orderDto.setId(1L);
        orderDto.setOrderDate(LocalDate.of(1970, 1, 1));
        orderDto.setOrderTime("Order Time");
        orderDto.setProducts(new ArrayList<>());
        orderDto.setTotalPriceInEur(10.0d);
        when(orderService.createOrder(Mockito.<OrderDto>any())).thenReturn(orderDto);

        OrderItemLineDto orderItemLineDto = new OrderItemLineDto();
        orderItemLineDto.setId(2L);
        orderItemLineDto.setProductName("42");
        orderItemLineDto.setProductPriceInEur(0.5d);
        orderItemLineDto.setQuantity(0);
        orderItemLineDto.setShortDescriptionProduct("42");
        orderItemLineDto.setTotalPrice(0.5d);

        ArrayList<OrderItemLineDto> products = new ArrayList<>();
        products.add(orderItemLineDto);

        OrderDto orderDto2 = new OrderDto();
        orderDto2.setCustomerCompany("Customer Company");
        orderDto2.setCustomerFirstName("Jane");
        orderDto2.setCustomerLastName("Doe");
        orderDto2.setId(1L);
        orderDto2.setOrderDate(null);
        orderDto2.setOrderTime("Order Time");
        orderDto2.setProducts(products);
        orderDto2.setTotalPriceInEur(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(orderDto2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"orderDate\":[1970,1,1],\"orderTime\":\"Order Time\",\"totalPriceInEur\":10.0,\"customerCompany\":\"Customer"
                                        + " Company\",\"customerFirstName\":\"Jane\",\"customerLastName\":\"Doe\",\"products\":[]}"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/orders/1"));
    }

    /**
     * Method under test: {@link OrderController#getOrders()}
     */
    @Test
    void testGetOrders() throws Exception {
        // Arrange
        when(orderService.getOrders()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OrderController#getOrderById(Long)}
     */
    @Test
    void testGetOrderById() throws Exception {
        // Arrange
        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerCompany("Customer Company");
        orderDto.setCustomerFirstName("Jane");
        orderDto.setCustomerLastName("Doe");
        orderDto.setId(1L);
        orderDto.setOrderDate(LocalDate.of(1970, 1, 1));
        orderDto.setOrderTime("Order Time");
        orderDto.setProducts(new ArrayList<>());
        orderDto.setTotalPriceInEur(10.0d);
        when(orderService.getOrderById(Mockito.<Long>any())).thenReturn(orderDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"orderDate\":[1970,1,1],\"orderTime\":\"Order Time\",\"totalPriceInEur\":10.0,\"customerCompany\":\"Customer"
                                        + " Company\",\"customerFirstName\":\"Jane\",\"customerLastName\":\"Doe\",\"products\":[]}"));
    }

    /**
     * Method under test: {@link OrderController#getOrdersByProduct(String)}
     */
    @Test
    void testGetOrdersByProduct() throws Exception {
        // Arrange
        when(orderService.getOrdersByProduct(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/product/{productName}",
                "Product Name");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OrderController#deleteProduct(Long)}
     */
    @Test
    void testDeleteProduct() throws Exception {
        // Arrange
        when(orderService.deleteOrder(Mockito.<Long>any())).thenReturn(1L);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/orders/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Order with id: \"1\"deleted from database"));
    }

    /**
     * Method under test: {@link OrderController#getOrdersByDate(LocalDate)}
     */
    @Test
    void testGetOrdersByDate() throws Exception {
        // Arrange
        when(orderService.getOrdersByOrderDate(Mockito.<LocalDate>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/date/{orderDate}",
                LocalDate.of(1970, 1, 1));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OrderController#getOrdersFromCustomer(String)}
     */
    @Test
    void testGetOrdersFromCustomer() throws Exception {
        // Arrange
        when(orderService.getOrdersByCustomer(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/orders/customer/{customerCompany}",
                "Customer Company");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

}
