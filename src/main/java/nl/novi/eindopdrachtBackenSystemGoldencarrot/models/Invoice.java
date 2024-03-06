package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;


import jakarta.persistence.*;

@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;
    //customer.firstNAme+lastName
    //customer.emailAdress
    //customer.phoneNumber

    @OneToOne
    private Order order;
    //order.orderDate;
    //order.orderTime;
    //order.products
    //order.totalPrice;


    private final String ourCompany = "The Golden Carrot";
    private final String ourAdress = "Industrieweg 338\n3455DC Utrecht";
    private final String ourPhoneNumber = "+3130 444 555 20";
    private final String IBAN = "NL20ABNA0005623417";
    private final int ourTaxNumber = 815247011;

}

