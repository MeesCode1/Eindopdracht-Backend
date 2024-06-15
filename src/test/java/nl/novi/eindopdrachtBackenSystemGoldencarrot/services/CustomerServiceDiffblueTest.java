package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.customerDtos.CustomerDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.customerDtos.CustomerDtoUpdate;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ResourceNotFoundException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Customer;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CustomerService.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceDiffblueTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    /**
     * Method under test: {@link CustomerService#createCustomer(CustomerDto)}
     */
    @Test
    void testCreateCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setBankAccount("3");
        customer.setCompany("Company");
        customer.setDob(LocalDate.of(1970, 1, 1));
        customer.setEmailAddress("42 Main St");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setOrders(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer);

        CustomerDto cDto = new CustomerDto();
        cDto.setAddress("42 Main St");
        cDto.setBankAccount("3");
        cDto.setCompany("Company");
        cDto.setDob(LocalDate.of(1970, 1, 1));
        cDto.setEmailAddress("42 Main St");
        cDto.setFirstName("Jane");
        cDto.setId(1L);
        cDto.setLastName("Doe");
        cDto.setPhoneNumber("6625550144");

        // Act
        CustomerDto actualCreateCustomerResult = customerService.createCustomer(cDto);

        // Assert
        verify(customerRepository).save(isA(Customer.class));
        assertEquals("1970-01-01", actualCreateCustomerResult.getDob().toString());
        assertEquals("3", actualCreateCustomerResult.getBankAccount());
        assertEquals("42 Main St", actualCreateCustomerResult.getAddress());
        assertEquals("42 Main St", actualCreateCustomerResult.getEmailAddress());
        assertEquals("6625550144", actualCreateCustomerResult.getPhoneNumber());
        assertEquals("Company", actualCreateCustomerResult.getCompany());
        assertEquals("Doe", actualCreateCustomerResult.getLastName());
        assertEquals("Jane", actualCreateCustomerResult.getFirstName());
        assertEquals(1L, actualCreateCustomerResult.getId().longValue());
    }

    /**
     * Method under test: {@link CustomerService#createCustomer(CustomerDto)}
     */
    @Test
    void testCreateCustomer2() {
        // Arrange
        when(customerRepository.save(Mockito.<Customer>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));

        CustomerDto cDto = new CustomerDto();
        cDto.setAddress("42 Main St");
        cDto.setBankAccount("3");
        cDto.setCompany("Company");
        cDto.setDob(LocalDate.of(1970, 1, 1));
        cDto.setEmailAddress("42 Main St");
        cDto.setFirstName("Jane");
        cDto.setId(1L);
        cDto.setLastName("Doe");
        cDto.setPhoneNumber("6625550144");

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> customerService.createCustomer(cDto));
        verify(customerRepository).save(isA(Customer.class));
    }

    /**
     * Method under test: {@link CustomerService#getCustomerByCompany(String)}
     */
    @Test
    void testGetCustomerByCompany() {
        // Arrange
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setBankAccount("3");
        customer.setCompany("Company");
        customer.setDob(LocalDate.of(1970, 1, 1));
        customer.setEmailAddress("42 Main St");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setOrders(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findByCompany(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        CustomerDto actualCustomerByCompany = customerService.getCustomerByCompany("Company");

        // Assert
        verify(customerRepository).findByCompany(eq("Company"));
        assertEquals("1970-01-01", actualCustomerByCompany.getDob().toString());
        assertEquals("3", actualCustomerByCompany.getBankAccount());
        assertEquals("42 Main St", actualCustomerByCompany.getAddress());
        assertEquals("42 Main St", actualCustomerByCompany.getEmailAddress());
        assertEquals("6625550144", actualCustomerByCompany.getPhoneNumber());
        assertEquals("Company", actualCustomerByCompany.getCompany());
        assertEquals("Doe", actualCustomerByCompany.getLastName());
        assertEquals("Jane", actualCustomerByCompany.getFirstName());
        assertEquals(1L, actualCustomerByCompany.getId().longValue());
    }

    /**
     * Method under test: {@link CustomerService#getCustomerByCompany(String)}
     */
    @Test
    void testGetCustomerByCompany2() {
        // Arrange
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findByCompany(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomerByCompany("Company"));
        verify(customerRepository).findByCompany(eq("Company"));
    }

    /**
     * Method under test: {@link CustomerService#getCustomerByCompany(String)}
     */
    @Test
    void testGetCustomerByCompany3() {
        // Arrange
        when(customerRepository.findByCompany(Mockito.<String>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomerByCompany("Company"));
        verify(customerRepository).findByCompany(eq("Company"));
    }

    /**
     * Method under test: {@link CustomerService#getAllCustomers()}
     */
    @Test
    void testGetAllCustomers() {
        // Arrange
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<CustomerDto> actualAllCustomers = customerService.getAllCustomers();

        // Assert
        verify(customerRepository).findAll();
        assertTrue(actualAllCustomers.isEmpty());
    }

    /**
     * Method under test: {@link CustomerService#getAllCustomers()}
     */
    @Test
    void testGetAllCustomers2() {
        // Arrange
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setBankAccount("3");
        customer.setCompany("Company");
        customer.setDob(LocalDate.of(1970, 1, 1));
        customer.setEmailAddress("42 Main St");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setOrders(new ArrayList<>());
        customer.setPhoneNumber("6625550144");

        ArrayList<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        when(customerRepository.findAll()).thenReturn(customerList);

        // Act
        List<CustomerDto> actualAllCustomers = customerService.getAllCustomers();

        // Assert
        verify(customerRepository).findAll();
        assertEquals(1, actualAllCustomers.size());
    }

    /**
     * Method under test: {@link CustomerService#getAllCustomers()}
     */
    @Test
    void testGetAllCustomers3() {
        // Arrange
        when(customerRepository.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> customerService.getAllCustomers());
        verify(customerRepository).findAll();
    }

    /**
     * Method under test:
     * {@link CustomerService#updateCustomer(String, CustomerDtoUpdate)}
     */
    @Test
    void testUpdateCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setBankAccount("3");
        customer.setCompany("Company");
        customer.setDob(LocalDate.of(1970, 1, 1));
        customer.setEmailAddress("42 Main St");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setOrders(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setBankAccount("3");
        customer2.setCompany("Company");
        customer2.setDob(LocalDate.of(1970, 1, 1));
        customer2.setEmailAddress("42 Main St");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setOrders(new ArrayList<>());
        customer2.setPhoneNumber("6625550144");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findByCompany(Mockito.<String>any())).thenReturn(ofResult);
        CustomerDtoUpdate cdto = new CustomerDtoUpdate();
        cdto.address = "42 Main St";
        cdto.bankAccount = "3";
        cdto.company = "Company";
        cdto.dob = LocalDate.of(1970, 1, 1);
        cdto.emailAddress = "42 Main St";
        cdto.firstName = "Jane";
        cdto.lastName = "Doe";
        cdto.phoneNumber = "6625550144";

        // Act
        CustomerDto actualUpdateCustomerResult = customerService.updateCustomer("Company", cdto);

        // Assert
        verify(customerRepository).findByCompany(eq("Company"));
        verify(customerRepository).save(isA(Customer.class));
        assertEquals("1970-01-01", actualUpdateCustomerResult.getDob().toString());
        assertEquals("3", actualUpdateCustomerResult.getBankAccount());
        assertEquals("42 Main St", actualUpdateCustomerResult.getAddress());
        assertEquals("42 Main St", actualUpdateCustomerResult.getEmailAddress());
        assertEquals("6625550144", actualUpdateCustomerResult.getPhoneNumber());
        assertEquals("Company", actualUpdateCustomerResult.getCompany());
        assertEquals("Doe", actualUpdateCustomerResult.getLastName());
        assertEquals("Jane", actualUpdateCustomerResult.getFirstName());
        assertEquals(1L, actualUpdateCustomerResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link CustomerService#updateCustomer(String, CustomerDtoUpdate)}
     */
    @Test
    void testUpdateCustomer2() {
        // Arrange
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setBankAccount("3");
        customer.setCompany("Company");
        customer.setDob(LocalDate.of(1970, 1, 1));
        customer.setEmailAddress("42 Main St");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setOrders(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.save(Mockito.<Customer>any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        when(customerRepository.findByCompany(Mockito.<String>any())).thenReturn(ofResult);
        CustomerDtoUpdate cdto = new CustomerDtoUpdate();
        cdto.address = "42 Main St";
        cdto.bankAccount = "3";
        cdto.company = "Company";
        cdto.dob = LocalDate.of(1970, 1, 1);
        cdto.emailAddress = "42 Main St";
        cdto.firstName = "Jane";
        cdto.lastName = "Doe";
        cdto.phoneNumber = "6625550144";

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> customerService.updateCustomer("Company", cdto));
        verify(customerRepository).findByCompany(eq("Company"));
        verify(customerRepository).save(isA(Customer.class));
    }

    /**
     * Method under test:
     * {@link CustomerService#updateCustomer(String, CustomerDtoUpdate)}
     */
    @Test
    void testUpdateCustomer3() {
        // Arrange
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findByCompany(Mockito.<String>any())).thenReturn(emptyResult);
        CustomerDtoUpdate cdto = new CustomerDtoUpdate();
        cdto.address = "42 Main St";
        cdto.bankAccount = "3";
        cdto.company = "Company";
        cdto.dob = LocalDate.of(1970, 1, 1);
        cdto.emailAddress = "42 Main St";
        cdto.firstName = "Jane";
        cdto.lastName = "Doe";
        cdto.phoneNumber = "6625550144";

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> customerService.updateCustomer("Company", cdto));
        verify(customerRepository).findByCompany(eq("Company"));
    }

    /**
     * Method under test:
     * {@link CustomerService#updateCustomer(String, CustomerDtoUpdate)}
     */
    @Test
    void testUpdateCustomer4() {
        // Arrange
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setBankAccount("3");
        customer.setCompany("Company");
        customer.setDob(LocalDate.of(1970, 1, 1));
        customer.setEmailAddress("42 Main St");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setOrders(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setBankAccount("3");
        customer2.setCompany("Company");
        customer2.setDob(LocalDate.of(1970, 1, 1));
        customer2.setEmailAddress("42 Main St");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setOrders(new ArrayList<>());
        customer2.setPhoneNumber("6625550144");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findByCompany(Mockito.<String>any())).thenReturn(ofResult);
        CustomerDtoUpdate cdto = new CustomerDtoUpdate();
        cdto.address = null;
        cdto.bankAccount = "3";
        cdto.company = "Company";
        cdto.dob = LocalDate.of(1970, 1, 1);
        cdto.emailAddress = "42 Main St";
        cdto.firstName = "Jane";
        cdto.lastName = "Doe";
        cdto.phoneNumber = "6625550144";

        // Act
        CustomerDto actualUpdateCustomerResult = customerService.updateCustomer("Company", cdto);

        // Assert
        verify(customerRepository).findByCompany(eq("Company"));
        verify(customerRepository).save(isA(Customer.class));
        assertEquals("1970-01-01", actualUpdateCustomerResult.getDob().toString());
        assertEquals("3", actualUpdateCustomerResult.getBankAccount());
        assertEquals("42 Main St", actualUpdateCustomerResult.getAddress());
        assertEquals("42 Main St", actualUpdateCustomerResult.getEmailAddress());
        assertEquals("6625550144", actualUpdateCustomerResult.getPhoneNumber());
        assertEquals("Company", actualUpdateCustomerResult.getCompany());
        assertEquals("Doe", actualUpdateCustomerResult.getLastName());
        assertEquals("Jane", actualUpdateCustomerResult.getFirstName());
        assertEquals(1L, actualUpdateCustomerResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link CustomerService#updateCustomer(String, CustomerDtoUpdate)}
     */
    @Test
    void testUpdateCustomer5() {
        // Arrange
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setBankAccount("3");
        customer.setCompany("Company");
        customer.setDob(LocalDate.of(1970, 1, 1));
        customer.setEmailAddress("42 Main St");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setOrders(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setBankAccount("3");
        customer2.setCompany("Company");
        customer2.setDob(LocalDate.of(1970, 1, 1));
        customer2.setEmailAddress("42 Main St");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setOrders(new ArrayList<>());
        customer2.setPhoneNumber("6625550144");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findByCompany(Mockito.<String>any())).thenReturn(ofResult);
        CustomerDtoUpdate cdto = new CustomerDtoUpdate();
        cdto.address = "42 Main St";
        cdto.bankAccount = null;
        cdto.company = "Company";
        cdto.dob = LocalDate.of(1970, 1, 1);
        cdto.emailAddress = "42 Main St";
        cdto.firstName = "Jane";
        cdto.lastName = "Doe";
        cdto.phoneNumber = "6625550144";

        // Act
        CustomerDto actualUpdateCustomerResult = customerService.updateCustomer("Company", cdto);

        // Assert
        verify(customerRepository).findByCompany(eq("Company"));
        verify(customerRepository).save(isA(Customer.class));
        assertEquals("1970-01-01", actualUpdateCustomerResult.getDob().toString());
        assertEquals("3", actualUpdateCustomerResult.getBankAccount());
        assertEquals("42 Main St", actualUpdateCustomerResult.getAddress());
        assertEquals("42 Main St", actualUpdateCustomerResult.getEmailAddress());
        assertEquals("6625550144", actualUpdateCustomerResult.getPhoneNumber());
        assertEquals("Company", actualUpdateCustomerResult.getCompany());
        assertEquals("Doe", actualUpdateCustomerResult.getLastName());
        assertEquals("Jane", actualUpdateCustomerResult.getFirstName());
        assertEquals(1L, actualUpdateCustomerResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link CustomerService#updateCustomer(String, CustomerDtoUpdate)}
     */
    @Test
    void testUpdateCustomer6() {
        // Arrange
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setBankAccount("3");
        customer.setCompany("Company");
        customer.setDob(LocalDate.of(1970, 1, 1));
        customer.setEmailAddress("42 Main St");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setOrders(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setBankAccount("3");
        customer2.setCompany("Company");
        customer2.setDob(LocalDate.of(1970, 1, 1));
        customer2.setEmailAddress("42 Main St");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setOrders(new ArrayList<>());
        customer2.setPhoneNumber("6625550144");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findByCompany(Mockito.<String>any())).thenReturn(ofResult);
        CustomerDtoUpdate cdto = new CustomerDtoUpdate();
        cdto.address = "42 Main St";
        cdto.bankAccount = "3";
        cdto.company = null;
        cdto.dob = LocalDate.of(1970, 1, 1);
        cdto.emailAddress = "42 Main St";
        cdto.firstName = "Jane";
        cdto.lastName = "Doe";
        cdto.phoneNumber = "6625550144";

        // Act
        CustomerDto actualUpdateCustomerResult = customerService.updateCustomer("Company", cdto);

        // Assert
        verify(customerRepository).findByCompany(eq("Company"));
        verify(customerRepository).save(isA(Customer.class));
        assertEquals("1970-01-01", actualUpdateCustomerResult.getDob().toString());
        assertEquals("3", actualUpdateCustomerResult.getBankAccount());
        assertEquals("42 Main St", actualUpdateCustomerResult.getAddress());
        assertEquals("42 Main St", actualUpdateCustomerResult.getEmailAddress());
        assertEquals("6625550144", actualUpdateCustomerResult.getPhoneNumber());
        assertEquals("Company", actualUpdateCustomerResult.getCompany());
        assertEquals("Doe", actualUpdateCustomerResult.getLastName());
        assertEquals("Jane", actualUpdateCustomerResult.getFirstName());
        assertEquals(1L, actualUpdateCustomerResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link CustomerService#updateCustomer(String, CustomerDtoUpdate)}
     */
    @Test
    void testUpdateCustomer7() {
        // Arrange
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setBankAccount("3");
        customer.setCompany("Company");
        customer.setDob(LocalDate.of(1970, 1, 1));
        customer.setEmailAddress("42 Main St");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setOrders(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setBankAccount("3");
        customer2.setCompany("Company");
        customer2.setDob(LocalDate.of(1970, 1, 1));
        customer2.setEmailAddress("42 Main St");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setOrders(new ArrayList<>());
        customer2.setPhoneNumber("6625550144");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findByCompany(Mockito.<String>any())).thenReturn(ofResult);
        CustomerDtoUpdate cdto = new CustomerDtoUpdate();
        cdto.address = "42 Main St";
        cdto.bankAccount = "3";
        cdto.company = "Company";
        cdto.dob = null;
        cdto.emailAddress = "42 Main St";
        cdto.firstName = "Jane";
        cdto.lastName = "Doe";
        cdto.phoneNumber = "6625550144";

        // Act
        CustomerDto actualUpdateCustomerResult = customerService.updateCustomer("Company", cdto);

        // Assert
        verify(customerRepository).findByCompany(eq("Company"));
        verify(customerRepository).save(isA(Customer.class));
        assertEquals("1970-01-01", actualUpdateCustomerResult.getDob().toString());
        assertEquals("3", actualUpdateCustomerResult.getBankAccount());
        assertEquals("42 Main St", actualUpdateCustomerResult.getAddress());
        assertEquals("42 Main St", actualUpdateCustomerResult.getEmailAddress());
        assertEquals("6625550144", actualUpdateCustomerResult.getPhoneNumber());
        assertEquals("Company", actualUpdateCustomerResult.getCompany());
        assertEquals("Doe", actualUpdateCustomerResult.getLastName());
        assertEquals("Jane", actualUpdateCustomerResult.getFirstName());
        assertEquals(1L, actualUpdateCustomerResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link CustomerService#updateCustomer(String, CustomerDtoUpdate)}
     */
    @Test
    void testUpdateCustomer8() {
        // Arrange
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setBankAccount("3");
        customer.setCompany("Company");
        customer.setDob(LocalDate.of(1970, 1, 1));
        customer.setEmailAddress("42 Main St");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setOrders(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setBankAccount("3");
        customer2.setCompany("Company");
        customer2.setDob(LocalDate.of(1970, 1, 1));
        customer2.setEmailAddress("42 Main St");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setOrders(new ArrayList<>());
        customer2.setPhoneNumber("6625550144");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findByCompany(Mockito.<String>any())).thenReturn(ofResult);
        CustomerDtoUpdate cdto = new CustomerDtoUpdate();
        cdto.address = "42 Main St";
        cdto.bankAccount = "3";
        cdto.company = "Company";
        cdto.dob = LocalDate.of(1970, 1, 1);
        cdto.emailAddress = null;
        cdto.firstName = "Jane";
        cdto.lastName = "Doe";
        cdto.phoneNumber = "6625550144";

        // Act
        CustomerDto actualUpdateCustomerResult = customerService.updateCustomer("Company", cdto);

        // Assert
        verify(customerRepository).findByCompany(eq("Company"));
        verify(customerRepository).save(isA(Customer.class));
        assertEquals("1970-01-01", actualUpdateCustomerResult.getDob().toString());
        assertEquals("3", actualUpdateCustomerResult.getBankAccount());
        assertEquals("42 Main St", actualUpdateCustomerResult.getAddress());
        assertEquals("42 Main St", actualUpdateCustomerResult.getEmailAddress());
        assertEquals("6625550144", actualUpdateCustomerResult.getPhoneNumber());
        assertEquals("Company", actualUpdateCustomerResult.getCompany());
        assertEquals("Doe", actualUpdateCustomerResult.getLastName());
        assertEquals("Jane", actualUpdateCustomerResult.getFirstName());
        assertEquals(1L, actualUpdateCustomerResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link CustomerService#updateCustomer(String, CustomerDtoUpdate)}
     */
    @Test
    void testUpdateCustomer9() {
        // Arrange
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setBankAccount("3");
        customer.setCompany("Company");
        customer.setDob(LocalDate.of(1970, 1, 1));
        customer.setEmailAddress("42 Main St");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setOrders(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setBankAccount("3");
        customer2.setCompany("Company");
        customer2.setDob(LocalDate.of(1970, 1, 1));
        customer2.setEmailAddress("42 Main St");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setOrders(new ArrayList<>());
        customer2.setPhoneNumber("6625550144");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findByCompany(Mockito.<String>any())).thenReturn(ofResult);
        CustomerDtoUpdate cdto = new CustomerDtoUpdate();
        cdto.address = "42 Main St";
        cdto.bankAccount = "3";
        cdto.company = "Company";
        cdto.dob = LocalDate.of(1970, 1, 1);
        cdto.emailAddress = "42 Main St";
        cdto.firstName = null;
        cdto.lastName = "Doe";
        cdto.phoneNumber = "6625550144";

        // Act
        CustomerDto actualUpdateCustomerResult = customerService.updateCustomer("Company", cdto);

        // Assert
        verify(customerRepository).findByCompany(eq("Company"));
        verify(customerRepository).save(isA(Customer.class));
        assertEquals("1970-01-01", actualUpdateCustomerResult.getDob().toString());
        assertEquals("3", actualUpdateCustomerResult.getBankAccount());
        assertEquals("42 Main St", actualUpdateCustomerResult.getAddress());
        assertEquals("42 Main St", actualUpdateCustomerResult.getEmailAddress());
        assertEquals("6625550144", actualUpdateCustomerResult.getPhoneNumber());
        assertEquals("Company", actualUpdateCustomerResult.getCompany());
        assertEquals("Doe", actualUpdateCustomerResult.getLastName());
        assertEquals("Jane", actualUpdateCustomerResult.getFirstName());
        assertEquals(1L, actualUpdateCustomerResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link CustomerService#updateCustomer(String, CustomerDtoUpdate)}
     */
    @Test
    void testUpdateCustomer10() {
        // Arrange
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setBankAccount("3");
        customer.setCompany("Company");
        customer.setDob(LocalDate.of(1970, 1, 1));
        customer.setEmailAddress("42 Main St");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setOrders(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setBankAccount("3");
        customer2.setCompany("Company");
        customer2.setDob(LocalDate.of(1970, 1, 1));
        customer2.setEmailAddress("42 Main St");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setOrders(new ArrayList<>());
        customer2.setPhoneNumber("6625550144");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findByCompany(Mockito.<String>any())).thenReturn(ofResult);
        CustomerDtoUpdate cdto = new CustomerDtoUpdate();
        cdto.address = "42 Main St";
        cdto.bankAccount = "3";
        cdto.company = "Company";
        cdto.dob = LocalDate.of(1970, 1, 1);
        cdto.emailAddress = "42 Main St";
        cdto.firstName = "Jane";
        cdto.lastName = null;
        cdto.phoneNumber = "6625550144";

        // Act
        CustomerDto actualUpdateCustomerResult = customerService.updateCustomer("Company", cdto);

        // Assert
        verify(customerRepository).findByCompany(eq("Company"));
        verify(customerRepository).save(isA(Customer.class));
        assertEquals("1970-01-01", actualUpdateCustomerResult.getDob().toString());
        assertEquals("3", actualUpdateCustomerResult.getBankAccount());
        assertEquals("42 Main St", actualUpdateCustomerResult.getAddress());
        assertEquals("42 Main St", actualUpdateCustomerResult.getEmailAddress());
        assertEquals("6625550144", actualUpdateCustomerResult.getPhoneNumber());
        assertEquals("Company", actualUpdateCustomerResult.getCompany());
        assertEquals("Doe", actualUpdateCustomerResult.getLastName());
        assertEquals("Jane", actualUpdateCustomerResult.getFirstName());
        assertEquals(1L, actualUpdateCustomerResult.getId().longValue());
    }

    /**
     * Method under test:
     * {@link CustomerService#updateCustomer(String, CustomerDtoUpdate)}
     */
    @Test
    void testUpdateCustomer11() {
        // Arrange
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setBankAccount("3");
        customer.setCompany("Company");
        customer.setDob(LocalDate.of(1970, 1, 1));
        customer.setEmailAddress("42 Main St");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setOrders(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        Optional<Customer> ofResult = Optional.of(customer);

        Customer customer2 = new Customer();
        customer2.setAddress("42 Main St");
        customer2.setBankAccount("3");
        customer2.setCompany("Company");
        customer2.setDob(LocalDate.of(1970, 1, 1));
        customer2.setEmailAddress("42 Main St");
        customer2.setFirstName("Jane");
        customer2.setId(1L);
        customer2.setLastName("Doe");
        customer2.setOrders(new ArrayList<>());
        customer2.setPhoneNumber("6625550144");
        when(customerRepository.save(Mockito.<Customer>any())).thenReturn(customer2);
        when(customerRepository.findByCompany(Mockito.<String>any())).thenReturn(ofResult);
        CustomerDtoUpdate cdto = new CustomerDtoUpdate();
        cdto.address = "42 Main St";
        cdto.bankAccount = "3";
        cdto.company = "Company";
        cdto.dob = LocalDate.of(1970, 1, 1);
        cdto.emailAddress = "42 Main St";
        cdto.firstName = "Jane";
        cdto.lastName = "Doe";
        cdto.phoneNumber = null;

        // Act
        CustomerDto actualUpdateCustomerResult = customerService.updateCustomer("Company", cdto);

        // Assert
        verify(customerRepository).findByCompany(eq("Company"));
        verify(customerRepository).save(isA(Customer.class));
        assertEquals("1970-01-01", actualUpdateCustomerResult.getDob().toString());
        assertEquals("3", actualUpdateCustomerResult.getBankAccount());
        assertEquals("42 Main St", actualUpdateCustomerResult.getAddress());
        assertEquals("42 Main St", actualUpdateCustomerResult.getEmailAddress());
        assertEquals("6625550144", actualUpdateCustomerResult.getPhoneNumber());
        assertEquals("Company", actualUpdateCustomerResult.getCompany());
        assertEquals("Doe", actualUpdateCustomerResult.getLastName());
        assertEquals("Jane", actualUpdateCustomerResult.getFirstName());
        assertEquals(1L, actualUpdateCustomerResult.getId().longValue());
    }

    /**
     * Method under test: {@link CustomerService#deleteCustomer(Long)}
     */
    @Test
    void testDeleteCustomer() {
        // Arrange
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setBankAccount("3");
        customer.setCompany("Company");
        customer.setDob(LocalDate.of(1970, 1, 1));
        customer.setEmailAddress("42 Main St");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setOrders(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        Optional<Customer> ofResult = Optional.of(customer);
        doNothing().when(customerRepository).delete(Mockito.<Customer>any());
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        String actualDeleteCustomerResult = customerService.deleteCustomer(1L);

        // Assert
        verify(customerRepository).delete(isA(Customer.class));
        verify(customerRepository).findById(eq(1L));
        assertEquals("Company", actualDeleteCustomerResult);
    }

    /**
     * Method under test: {@link CustomerService#deleteCustomer(Long)}
     */
    @Test
    void testDeleteCustomer2() {
        // Arrange
        Customer customer = new Customer();
        customer.setAddress("42 Main St");
        customer.setBankAccount("3");
        customer.setCompany("Company");
        customer.setDob(LocalDate.of(1970, 1, 1));
        customer.setEmailAddress("42 Main St");
        customer.setFirstName("Jane");
        customer.setId(1L);
        customer.setLastName("Doe");
        customer.setOrders(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        Optional<Customer> ofResult = Optional.of(customer);
        doThrow(new ResourceNotFoundException("An error occurred")).when(customerRepository)
                .delete(Mockito.<Customer>any());
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> customerService.deleteCustomer(1L));
        verify(customerRepository).delete(isA(Customer.class));
        verify(customerRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link CustomerService#deleteCustomer(Long)}
     */
    @Test
    void testDeleteCustomer3() {
        // Arrange
        Optional<Customer> emptyResult = Optional.empty();
        when(customerRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> customerService.deleteCustomer(1L));
        verify(customerRepository).findById(eq(1L));
    }
}
