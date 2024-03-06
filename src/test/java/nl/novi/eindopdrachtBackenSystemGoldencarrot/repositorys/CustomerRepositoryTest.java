package nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.CustomerDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository repos;



    @Test
    void shouldSaveAndReturnCustomer(){

        Customer c = new Customer();
        c.setFirstName("Harrie");
        c.setLastName("Snijders");
        c.setCompany("Bistro beachclub");
        c.setPhoneNumber("0688889999");

        Customer savedCustomer = repos.save(c);

        Assertions.assertNotNull(savedCustomer);
        Assertions.assertTrue (savedCustomer.getId() > 0);
    }

    @Test
    void shouldSaveAndReturnAllCustomers(){

        Customer c = new Customer();
        c.setFirstName("Harrie");
        c.setLastName("Snijders");
        c.setCompany("Bistro beachclub");
        c.setPhoneNumber("0688889999");

        Customer c2 = new Customer();
        c2.setFirstName("Arjen");
        c2.setLastName("Robben");
        c2.setCompany("PSV");
        c2.setPhoneNumber("0699998765");

        repos.save(c);
        repos.save(c2);

        List<Customer>savedCustomers = repos.findAll();


        Assertions.assertNotNull(savedCustomers);
        Assertions.assertTrue (savedCustomers.size() == 2);
        assertEquals(c.getCompany(), savedCustomers.get(0).getCompany());
        assertEquals(c2.getId(), savedCustomers.get(1).getId());
    }

@Test
    void shouldReturnCorrectCustomerByCompany(){

    Customer c = new Customer();
    c.setFirstName("Harrie");
    c.setLastName("Snijders");
    c.setCompany("Bistro beachclub");
    c.setPhoneNumber("0688889999");

    repos.save(c);

    Customer resultCustomer = repos.findByCompany("Bistro beachclub").get();

    Assertions.assertNotNull(resultCustomer);
    assertEquals(c.getFirstName(), resultCustomer.getFirstName());
}


}
