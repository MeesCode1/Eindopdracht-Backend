package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import jakarta.mail.MessagingException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.UserEmployeeDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Role;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.UserEmployee;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.UserEmployeeRepository;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserEmployeeServiceTest {

    @Mock
    UserEmployeeRepository repos;

    @InjectMocks
    UserEmployeeService service;

    @Test
    void shouldReturnCorrectUserEmployee() throws MessagingException {

        Role role1 = new Role();
        role1.setRoleName("CEO");
        role1.setId(1L);
        List<Role> roles = new ArrayList<>();
        roles.add(role1);

            UserEmployee userEmployee = new UserEmployee();
                userEmployee.setEmployeeNumber(1L);
                userEmployee.setUsername("GuusMeeuwis");
                userEmployee.setPassword("topsecret");
                userEmployee.setFirstName("Guus");
                userEmployee.setLastName("Meeuwis");
                userEmployee.setDob(LocalDate.of(1972,02,23));
                userEmployee.setAddress("Muziekweg 38 Eindhoven");
                userEmployee.setPhoneNumb("+31622772277");
                userEmployee.setEmailAddress("test mail@mail.com");
                userEmployee.setBankAccount("NL25INGB0003123456");
                userEmployee.setFunction("CEO");
                userEmployee.setRoles(roles);

        Mockito.when(repos.findByEmployeeNumber(any())).thenReturn(Optional.of(userEmployee));

        UserEmployeeDto resultUdto = service.getUserEmployee(1L);

        assertEquals("GuusMeeuwis", resultUdto.username);
        assertEquals("Muziekweg 38 Eindhoven", resultUdto.address);
        assertEquals("+31622772277", resultUdto.phoneNumb);
        assertEquals("CEO", resultUdto.getRoles().get(0));

    }
}
