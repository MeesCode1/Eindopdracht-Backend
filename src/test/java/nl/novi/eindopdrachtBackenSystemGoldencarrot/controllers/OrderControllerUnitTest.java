package nl.novi.eindopdrachtBackenSystemGoldencarrot.controllers;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.OrderDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.OrderItemLineDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.security.JwtService;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.CustomerService;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.services.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@WebMvcTest(OrderController.class)
    @ActiveProfiles("test")
    public class OrderControllerUnitTest {

        @Autowired
        MockMvc mockMvc;

        @MockBean
        JwtService jwtService;

        @MockBean
        OrderService orderService;

        @Test
        @WithMockUser(username = "testUser", roles = "JOKERCEO")
        void shouldRetreiveCorrectOrder() throws Exception {

            OrderItemLineDto ildto = new OrderItemLineDto();
            ildto.setId(1L);
            ildto.setProductName("Salmon side");
            ildto.setShortDescriptionProduct("Salmon side scaly");
            ildto.setProductPriceInEur(32.14);
            ildto.setQuantity(3);
            ildto.setTotalPrice(96.42);

            OrderItemLineDto ildto2 = new OrderItemLineDto();
            ildto2.setId(2L);
            ildto2.setProductName("Vine tomatoes");
            ildto2.setShortDescriptionProduct("Vine-tomatoes bio 1kg");
            ildto2.setProductPriceInEur(3.59);
            ildto2.setQuantity(2);
            ildto2.setTotalPrice(7.18);

            List<OrderItemLineDto> ildtos = new ArrayList<>();
            ildtos.add(ildto);
            ildtos.add(ildto2);

            OrderDto odto = new OrderDto();
            odto.setId(123L);
            odto.setCustomerCompany("Bistro Beachclub");
            odto.setCustomerFirstName("Harrie");
            odto.setCustomerLastName("Snijders");
            odto.setOrderDate(LocalDate.of(2023, 12, 31));
            odto.setOrderTime("15:11:00");
            odto.setProducts(ildtos);

            Mockito.when(orderService.getOrderById(odto.id)).thenReturn(odto);

            this.mockMvc
                    .perform(MockMvcRequestBuilders.get("/orders/123"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.customerCompany", is("Bistro Beachclub")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.customerFirstName", is("Harrie")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.customerLastName", is("Snijders")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.orderDate", is("2023-12-31")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.products.length()", is(2)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.products[0].productName", is("Salmon side")))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.products[1].productName", is("Vine tomatoes")));
        }


}
