package nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    public Long id;
    public LocalDate orderDate;
    public String orderTime;
    public double totalPriceInEur;
    @NotBlank
    public String customerCompany;

    public String customerFirstName;

    public String customerLastName;
    @NotEmpty
    @Size(min = 1)
    public List<OrderItemLineDto> products = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }


    public double getTotalPriceInEur() {
        return totalPriceInEur;
    }

    public void setTotalPriceInEur(double totalPriceInEur) {
        this.totalPriceInEur = totalPriceInEur;
    }

    public String getCustomerCompany() {
        return customerCompany;
    }

    public void setCustomerCompany(String customerCompany) {
        this.customerCompany = customerCompany;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public List<OrderItemLineDto> getProducts() {
        return products;
    }

    public void setProducts(List<OrderItemLineDto> products) {
        this.products = products;
    }
}

