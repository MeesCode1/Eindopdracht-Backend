package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.CustomerDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.OrderItemLineDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Customer;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Order;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.OrderItemLine;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.CustomerRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.OrderItemLineRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

    @ExtendWith(MockitoExtension.class)
    public class OrderItemLineServiceTest {

        @Mock
        OrderItemLineRepository repos;
        @InjectMocks
        OrderItemLineService service;



        @Test
        void shouldReturnCorrectOrderItemLines() {

            Order order = new Order();
            order.setId(111L);
            OrderItemLine il = new OrderItemLine();
            il.setId(1l);
            il.setOrder(order);

            Order order2 = new Order();
            order2.setId(222L);
            OrderItemLine il2 = new OrderItemLine();
            il2.setId(2l);
            il2.setOrder(order2);


            Mockito.when(repos.findByProduct_Name(anyString())).thenReturn(List.of(il, il2));


            List<OrderItemLine> resultIldtos= new ArrayList<>();
            resultIldtos = service.getOrderItemLinesByProduct("true input");

            assertEquals(1, resultIldtos.get(0).getId());
            assertEquals(111, resultIldtos.get(0).getOrder().getId());
            assertEquals(2, resultIldtos.get(1).getId());
            assertEquals(222, resultIldtos.get(1).getOrder().getId());
        }
}
