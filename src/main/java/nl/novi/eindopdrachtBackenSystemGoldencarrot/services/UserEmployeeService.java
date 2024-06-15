package nl.novi.eindopdrachtBackenSystemGoldencarrot.services;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.userEmployeeDtos.UserEmployeeDto;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.userEmployeeDtos.UserEmployeeDtoOutput;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.dtos.userEmployeeDtos.UserEmployeeDtoUpdate;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.IllegalArgumentException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.exception.ResourceNotFoundException;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Role;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.UserEmployee;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.RoleRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.UserEmployeeRepository;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.ModelMapperConfig;
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

    public UserEmployeeDtoOutput createUser(UserEmployeeDto udto) {

        Optional<UserEmployee> existingUser = repos.findByUsername(udto.username);
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Gebruiker \"" + udto.getUsername() + "\" bestaat al\n" +
                    "kies een andere gebruikersnaam");
        }

        String checkCorrectUsername = udto.firstName + udto.lastName;
        if (!udto.username.equals(checkCorrectUsername)) {
            throw new IllegalArgumentException("username requirement: username =" +
                    "FirstnameLastname");
        }

        UserEmployee newUser = ModelMapperConfig.mappingToEntityUserEmployee(udto);
        newUser.setPassword(pwEncoder.encode(udto.password));

        List<Role> roles = new ArrayList<>();
        for (String roleName : udto.roles) {
            Optional<Role> optionalRole = roleRepos.findByRoleName(roleName);

            roles.add(optionalRole.get());
        }
        newUser.setRoles(roles);

        newUser = repos.save(newUser);
        return ModelMapperConfig.mappingToDtoUserEmployee(repos.save(newUser));
    }

    public UserEmployeeDtoOutput getUserEmployee(Long employeeNumber) {
        UserEmployee userEmployee = repos.findByEmployeeNumber(employeeNumber).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found"));

        return ModelMapperConfig.mappingToDtoUserEmployee(userEmployee);
    }

    public List<UserEmployeeDtoOutput> getAllUserEmployees() {

        List<UserEmployeeDtoOutput> udtos = new ArrayList<>();

        Iterable<UserEmployee> useremployees = repos.findAll();
        for (UserEmployee user : useremployees) {
            UserEmployeeDtoOutput udto = ModelMapperConfig.mappingToDtoUserEmployee(user);

            udtos.add(udto);
        }
        return udtos;
    }

    public UserEmployeeDtoOutput updateUserEmployee(Long employeeNumber,
                                                    UserEmployeeDtoUpdate udto) {

        UserEmployee userEmployee = repos.findByEmployeeNumber(employeeNumber).orElseThrow(() ->
                new ResourceNotFoundException("UserEmployee not found"));

        if (udto.firstName != null || udto.lastName != null || udto.username != null) {
            String checkCorrectUsername = udto.firstName + udto.lastName;

            if (udto.username == null || !udto.username.equals(checkCorrectUsername)) {
                throw new IllegalArgumentException("username requirement: username =" +
                        "FirstnameLastname");
            }
        }
        if (udto.username != null) {
            userEmployee.setUsername(udto.username);
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

        userEmployee = repos.save(userEmployee);
        return ModelMapperConfig.mappingToDtoUserEmployee(userEmployee);
    }


    public String deleteUserEmployee(Long employeeNumber) {
        UserEmployee user = repos.findById(employeeNumber).orElseThrow(() ->
                new ResourceNotFoundException("Customer not found"));
        String username = user.getUsername();
        repos.delete(user);
        return username;
    }

}
