package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;

import jakarta.persistence.*;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer orderNumber;

    private String customerCompany;

    @Lob
    byte[] invoiceData;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumb) {
        this.orderNumber = orderNumb;
    }

    public String getCustomerCompany() {
        return customerCompany;
    }

    public void setCustomerCompany(String customerName) {
        this.customerCompany = customerName;
    }

    public byte[] getInvoiceData() {
        return invoiceData;
    }

    public void setInvoiceData(byte[] invoiceData) {
        this.invoiceData = invoiceData;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

