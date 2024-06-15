package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.customerDtos.CustomerDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ResourceNotFoundException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Customer;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
class CustomerServiceTest {

    @Mock
    CustomerRepository repos;
    @InjectMocks
    CustomerService service;


    @Test
    void shouldReturnCorrectCustomer() {
        Customer c = new Customer();
        c.setFirstName("Harrie");
        c.setLastName("Snijders");
        c.setCompany("Bistro beachclub");
        c.setPhoneNumber("0688889999");


        Mockito.when(repos.findByCompany(anyString())).thenReturn(Optional.of(c));


        CustomerDto savedCdto = new CustomerDto();
        savedCdto = service.getCustomerByCompany("Bistro beachclub");

        assertEquals("Harrie", savedCdto.firstName);
        assertEquals("Snijders", savedCdto.lastName);
        assertEquals("Bistro beachclub", savedCdto.company);
        assertEquals("0688889999", savedCdto.phoneNumber);
    }

    @Test
    void shouldThrowExceptionWhenCompanyNotFound() {
        Mockito.when(repos.findByCompany(anyString())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.getCustomerByCompany("NonexistentCompany");
        });
    }
}