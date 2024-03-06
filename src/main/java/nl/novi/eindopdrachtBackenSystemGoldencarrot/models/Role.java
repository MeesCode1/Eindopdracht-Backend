package nl.novi.eindopdrachtBackenSystemGoldencarrot.models;


import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Collection<UserEmployee> usersEmployees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Collection<UserEmployee> getUsersEmployees() {
        return usersEmployees;
    }

    public void setUsersEmployees(Collection<UserEmployee> usersEmployees) {
        this.usersEmployees = usersEmployees;
    }

    //    public Collection<nl.meesnovi.backendEindopdrDos.EindopdrachtBackendDos.models.UserEmployee> getUserEmployee() {
//        return userEmployees;
//    }
//
//    public void setUserEmployee(Collection<nl.meesnovi.backendEindopdrDos.EindopdrachtBackendDos.models.UserEmployee> userEmployees) {
//        userEmployee = userEmployees;
//    }
}

