package nl.novi.eindopdrachtBackenSystemGoldencarrot.security;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.UserEmployee;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.UserEmployeeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {
    private final UserEmployeeRepository userRepos;

    public MyUserDetailsService(UserEmployeeRepository userRepos) {
        this.userRepos = userRepos;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEmployee> ou = userRepos.findByUsername(username);

        if (ou.isPresent()) {
            UserEmployee user = ou.get();
            return new MyUserDetails(user);
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
