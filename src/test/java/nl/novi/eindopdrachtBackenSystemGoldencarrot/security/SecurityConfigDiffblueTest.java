package nl.novi.eindopdrachtBackenSystemGoldencarrot.security;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.UserEmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = {SecurityConfig.class})
@ExtendWith(SpringExtension.class)
class SecurityConfigDiffblueTest {
    @MockBean
    private JwtService jwtService;

    @Autowired
    private SecurityConfig securityConfig;

    @MockBean
    private UserEmployeeRepository userEmployeeRepository;

    /**
     * Method under test: {@link SecurityConfig#userDetailsService()}
     */
    @Test
    void testUserDetailsService() {
        // Arrange, Act and Assert
        assertTrue(securityConfig.userDetailsService() instanceof MyUserDetailsService);
    }

    /**
     * Method under test: {@link SecurityConfig#passwordEncoder()}
     */
    @Test
    void testPasswordEncoder() {
        // Arrange, Act and Assert
        assertTrue(securityConfig.passwordEncoder() instanceof BCryptPasswordEncoder);
    }

}
