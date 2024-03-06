package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.UserEmployeeDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ResourceNotFoundException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.generalMethodsComponent.ModelMapperConfig;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Role;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.UserEmployee;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.RoleRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.UserEmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserEmployeeService {
    private final UserEmployeeRepository repos;
    private final RoleRepository roleRepos;

    private final PasswordEncoder pwEncoder;



    public UserEmployeeService(UserEmployeeRepository repos, RoleRepository roleRepos,
                               PasswordEncoder pwEncoder) {

        this.repos = repos;
        this.roleRepos = roleRepos;
        this.pwEncoder = pwEncoder;
    }

    public UserEmployeeDto createUser(UserEmployeeDto udto) {

        Optional<UserEmployee> existingUser = repos.findByUsername(udto.username);
        if(existingUser.isPresent()){
            throw new IllegalArgumentException("Gebruiker \"" + udto.getUsername() + "\" bestaat al\n" +
                    "kies een andere gebruikersnaam");
        }

        UserEmployee newUser = ModelMapperConfig.mappingToEntityUserEmployee(udto);
        newUser.setPassword(pwEncoder.encode(udto.password));

        List<Role> roles = new ArrayList<>();
        for (String roleName : udto.roles) {
            Optional<Role> optionalRole = roleRepos.findByRoleName(roleName);

            roles.add(optionalRole.get());
        }

        newUser.setRoles(roles);
        repos.save(newUser);

        udto = ModelMapperConfig.mappingToDtoUserEmployee(newUser);

        return udto;

    }

    public UserEmployeeDto getUserEmployee(Long employeeNumber){
        UserEmployee userEmployee = repos.findByEmployeeNumber(employeeNumber).orElseThrow(()->
                new ResourceNotFoundException("Employee not found"));

        return ModelMapperConfig.mappingToDtoUserEmployee(userEmployee);
    }
    public List<UserEmployeeDto> getAllUserEmployees() {

        List<UserEmployeeDto> udtos = new ArrayList<>();

        Iterable<UserEmployee> useremployees = repos.findAll();
        for (UserEmployee user : useremployees) {
            UserEmployeeDto udto = ModelMapperConfig.mappingToDtoUserEmployee(user);

            udtos.add(udto);
        }
        return udtos;
    }

    public UserEmployeeDto updateUserEmployee(Long employeeNumber,
                                              UserEmployeeDto udto) {

        UserEmployee userEmployee = repos.findByEmployeeNumber(employeeNumber).orElseThrow(() ->
                new ResourceNotFoundException("UserEmployee not found"));

        if (udto.username != null) {
            userEmployee.setUsername(udto.username);
        }
        if (udto.password != null) {
            userEmployee.setPassword(udto.password);
        }
        if (udto.firstName != null) {
            userEmployee.setFirstName(udto.firstName);
        }
        if (udto.lastName != null) {
            userEmployee.setLastName(udto.lastName);
        }
        if (udto.dob != null) {
            userEmployee.setDob(udto.dob);
        }
        if (udto.address != null) {
            userEmployee.setAddress(udto.address);
        }
        if (udto.phoneNumb != null) {
            userEmployee.setPhoneNumb(udto.phoneNumb);
        }
            if (udto.emailAddress != null) {
                userEmployee.setEmailAddress(udto.emailAddress);
            }
            if (udto.bankAccount != null) {
                userEmployee.setBankAccount(udto.bankAccount);
            }
            if (udto.function != null) {
                userEmployee.setFunction(udto.function);
            }


            repos.save(userEmployee);
            udto = ModelMapperConfig.mappingToDtoUserEmployee(userEmployee);

            return udto;
        }

    }
