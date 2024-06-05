package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;


import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.customerDtos.CustomerDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.customerDtos.CustomerDtoUpdate;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ResourceNotFoundException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.ModelMapperConfig;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Customer;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository repos;


    public CustomerService(CustomerRepository repos) {
        this.repos = repos;
    }

    public CustomerDto createCustomer(CustomerDto cDto) {

        Customer c = ModelMapperConfig.mappingToEntityCustomer(cDto);

        cDto = ModelMapperConfig.mappingToDtoCustomer(repos.save(c));

        return cDto;

    }

    public CustomerDto getCustomerByCompany(String company) {
        Customer c = repos.findByCompany(company).orElseThrow(() ->
                new ResourceNotFoundException("customer not found"));

        CustomerDto cdto = ModelMapperConfig.mappingToDtoCustomer(c);
        return cdto;
    }

    public List<CustomerDto> getAllCustomers() {
        Iterable<Customer> customers = repos.findAll();

        List<CustomerDto> cDtos = new ArrayList<>();

        for (Customer c : customers) {

            CustomerDto cdto = ModelMapperConfig.mappingToDtoCustomer(c);

            cDtos.add(cdto);
        }
        return cDtos;
    }

    public CustomerDto updateCustomer(String company, CustomerDtoUpdate cdto) {
        Customer c = repos.findByCompany(company).orElseThrow(() ->
                new ResourceNotFoundException("Customer not found"));

        if (cdto.firstName != null) {
            c.setFirstName(cdto.firstName);
        }
        if (cdto.lastName != null) {
            c.setLastName(cdto.lastName);
        }
        if (cdto.company != null) {
            c.setCompany(cdto.company);
        }
        if (cdto.address != null) {
            c.setAddress(cdto.address);
        }
        if (cdto.dob != null) {
            c.setDob(cdto.dob);
        }
        if (cdto.emailAddress != null) {
            c.setEmailAddress(cdto.emailAddress);
        }
        if (cdto.phoneNumber != null) {
            c.setPhoneNumber(cdto.phoneNumber);
        }
        if (cdto.bankAccount != null) {
            c.setBankAccount(cdto.bankAccount);
        }

        Customer updatedCustomer = repos.save(c);

        return ModelMapperConfig.mappingToDtoCustomer(c);
    }

    public String deleteCustomer(Long id) {
        Customer c = repos.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer not found"));
        String companyName = c.getCompany();
        repos.delete(c);
        return companyName;
    }

}
