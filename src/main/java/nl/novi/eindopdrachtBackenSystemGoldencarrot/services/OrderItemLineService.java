package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.orderItemLineDtos.OrderItemLineDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ResourceNotFoundException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.OrderItemLine;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Product;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.OrderItemLineRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.OrderRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemLineService {

    private final OrderItemLineRepository repos;
    private final ProductRepository pRepos;
    private final OrderRepository oRepos;
    private final ProductService pService;

    public OrderItemLineService(OrderItemLineRepository repos, OrderRepository orderRepos,
                                ProductRepository productRepos, ProductService productService) {
        this.oRepos = orderRepos;
        this.repos = repos;
        this.pRepos = productRepos;
        this.pService = productService;
    }

    public OrderItemLine createOrderItemLine(Long orderId, OrderItemLineDto ildto) {

        OrderItemLine il = new OrderItemLine();

        il.setOrder(oRepos.findById(orderId).orElseThrow(() ->
                new ResourceNotFoundException("Order not found")));

        Product p = pRepos.findByName(ildto.productName).orElseThrow
                (() -> new ResourceNotFoundException("Product not familiar"));

        il.setProduct(p);
        il.setQuantity(ildto.quantity);

        pService.lowerInStockNewOrder(p.getName(), ildto.quantity);

        il.setTotalPrice(il.calculateTotalPrice());

        return repos.save(il);
    }

    public void updateOrderItemline(Long orderId, OrderItemLineDto newIldto) {

        OrderItemLine ilToUpdate = repos.findByOrder_IdAndProduct_Name(orderId, newIldto.productName)
                .orElse(null);

        Product p = pRepos.findByName(newIldto.productName).orElseThrow
                (() -> new ResourceNotFoundException("product not familiar"));

        boolean itemLineExistInOrder = ilToUpdate != null;

        if (itemLineExistInOrder & newIldto.quantity != 0) {

            pService.restoreInStockForChangedOrder(p.getName(), ilToUpdate.getQuantity());

            pService.lowerInStockNewOrder(p.getName(), newIldto.quantity);
            ilToUpdate.setQuantity(newIldto.quantity);

            ilToUpdate.setTotalPrice(ilToUpdate.calculateTotalPrice());

            repos.save(ilToUpdate);
        } else if (itemLineExistInOrder & newIldto.quantity == 0) {
            deleteOrderItemLine(p.getName(), orderId);
        } else {
            createOrderItemLine(orderId, newIldto);
        }
    }

    public void deleteOrderItemLine(String productName, Long orderId) {
        OrderItemLine il = repos.findByOrder_IdAndProduct_Name(orderId, productName)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        Product p = pRepos.findByName(productName).orElseThrow(() -> new
                ResourceNotFoundException("product not familiar"));
        pService.restoreInStockForChangedOrder(p.getName(), il.getQuantity());

        repos.delete(il);
    }

    public List<OrderItemLine> getOrderItemLinesByProduct(String productName) {

        return repos.findByProduct_Name(productName);
    }


}

