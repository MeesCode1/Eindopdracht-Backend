package nl.novi.eindopdrachtBackenSystemGoldencarrot.security;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.repositorys.UserEmployeeRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtService jwtService;
    private final UserEmployeeRepository userRepos;

    public SecurityConfig(JwtService service, UserEmployeeRepository userRepos) {
        this.jwtService = service;
        this.userRepos = userRepos;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder encoder, UserDetailsService udService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(udService)
                .passwordEncoder(encoder)
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService(this.userRepos);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .httpBasic().disable()
                .authorizeHttpRequests()

                //.requestMatchers(HttpMethod.GET, "/invoices/download/{id}").permitAll()
                .requestMatchers(HttpMethod.GET, "/invoices/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                .requestMatchers(HttpMethod.POST, "/image/{name}").permitAll()
                .requestMatchers(HttpMethod.POST, "/users_employees").hasAnyAuthority("CEO", "JOKER-CEO")
                .requestMatchers(HttpMethod.GET, "/users_employees").hasAnyAuthority("FINANCE-ADM", "CEO", "JOKER-CEO")
                .requestMatchers(HttpMethod.GET, "/users_employees/{employeenumber}").hasAnyAuthority("FINANCE-ADM", "CEO", "JOKER-CEO")
                .requestMatchers(HttpMethod.PUT, "/users_employees/{employeenumber}").hasAnyAuthority("CEO", "JOKER-CEO")
                .requestMatchers(HttpMethod.DELETE, "/users_employees/{employeenumber}").hasAnyAuthority("CEO", "JOKER-CEO")
                .requestMatchers(HttpMethod.POST, "/roles").hasAnyAuthority("CEO", "JOKER-CEO")
                .requestMatchers(HttpMethod.POST, "/products").hasAnyAuthority("FINANCE-ADM", "JOKER-CEO")
                .requestMatchers(HttpMethod.GET, "/products").hasAnyAuthority("SERVICE-DSK", "FINANCE-ADM", "CEO", "JOKER-CEO")
                .requestMatchers(HttpMethod.GET, "/products/{productname}").hasAnyAuthority("SERVICE-DSK", "FINANCE-ADM", "CEO", "JOKER-CEO")
                .requestMatchers(HttpMethod.GET, "/products/category/{category}").hasAnyAuthority("SERVICE-DSK", "FINANCE-ADM", "CEO", "JOKER-CEO")
                .requestMatchers(HttpMethod.PUT, "/products/{productname}").hasAnyAuthority("FINANCE-ADM", "JOKER-CEO")
                .requestMatchers(HttpMethod.PUT, "/products/increasestock/{productname}").hasAnyAuthority("FINANCE-ADM", "JOKER-CEO")
                .requestMatchers(HttpMethod.PUT, "/products/decreasestock/{productname}").hasAnyAuthority("FINANCE-ADM", "JOKER-CEO")
                .requestMatchers(HttpMethod.DELETE, "/products/{id}").hasAnyAuthority("FINANCE-ADM", "JOKER-CEO")
                .requestMatchers(HttpMethod.POST, "/customers").hasAnyAuthority("SERVICE-DSK", "JOKER-CEO")
                .requestMatchers(HttpMethod.GET, "/customers").hasAnyAuthority("SERVICE-DSK", "FINANCE-ADM", "CEO", "JOKER-CEO")
                .requestMatchers(HttpMethod.GET, "/customers/{company}").hasAnyAuthority("SERVICE-DSK", "FINANCE-ADM", "CEO", "JOKER-CEO")
                .requestMatchers(HttpMethod.PUT, "/customers/{company}").hasAnyAuthority("SERVICE-DSK", "JOKER-CEO")
                .requestMatchers(HttpMethod.DELETE, "/customers/{id}").hasAnyAuthority("SERVICE-DSK", "JOKER-CEO")
                .requestMatchers(HttpMethod.POST, "/orders").hasAnyAuthority("SERVICE-DSK", "JOKER-CEO")
                .requestMatchers(HttpMethod.GET, "/orders").hasAnyAuthority("SERVICE-DSK", "FINANCE-ADM", "CEO", "JOKER-CEO")
                .requestMatchers(HttpMethod.GET, "/orders/{id}").hasAnyAuthority("SERVICE-DSK", "FINANCE-ADM", "CEO", "JOKER-CEO")
                .requestMatchers(HttpMethod.GET, "/orders/customer/{customerCompany}").hasAnyAuthority("SERVICE-DSK", "FINANCE-ADM", "CEO", "JOKER-CEO")
                .requestMatchers(HttpMethod.GET, "/orders/product/{productName}").hasAnyAuthority("SERVICE-DSK", "FINANCE-ADM", "CEO", "JOKER-CEO")
                .requestMatchers(HttpMethod.GET, "/orders/date/{orderDate}").hasAnyAuthority("SERVICE-DSK", "FINANCE-ADM", "CEO", "JOKER-CEO")
                .requestMatchers(HttpMethod.PUT, "/orders/{id}").hasAnyAuthority("SERVICE-DSK", "JOKER-CEO")
                .requestMatchers(HttpMethod.PUT, "/orders/add_item/{id}").hasAnyAuthority("SERVICE-DSK", "JOKER-CEO")
                .requestMatchers(HttpMethod.PUT, "/orders/remove_item/{id}").hasAnyAuthority("SERVICE-DSK", "JOKER-CEO")
                .requestMatchers(HttpMethod.DELETE, "/orders/{id}").hasAnyAuthority("SERVICE-DSK", "JOKER-CEO")
                .requestMatchers(HttpMethod.GET, "/invoices/{id}").fullyAuthenticated()
//                .requestMatchers(HttpMethod.GET, "/invoices/download/{id}").fullyAuthenticated()
                .requestMatchers("/**").hasAnyAuthority("SERVICE-DSK", "FINANCE-ADM", "CEO", "JOKER-CEO")
                .anyRequest().denyAll()
                .and()
                .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }

}
