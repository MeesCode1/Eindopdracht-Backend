package nl.novi.eindopdrachtBackenSystemGoldencarrot.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.UserEmployee;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {JwtRequestFilter.class})
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
class JwtRequestFilterDiffblueTest {
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserDetailsService userDetailsService;

    /**
     * Method under test:
     * {@link JwtRequestFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal() throws ServletException, IOException {
        // Arrange
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
    }

    /**
     * Method under test:
     * {@link JwtRequestFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal2() throws ServletException, IOException {
        // Arrange
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("https://example.org/example");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
        verify(request).getHeader(eq("Authorization"));
    }

    /**
     * Method under test:
     * {@link JwtRequestFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal3() throws ServletException, IOException, UsernameNotFoundException {
        // Arrange
        UserEmployee user = new UserEmployee();
        user.setRoles(new ArrayList<>());
        MyUserDetails myUserDetails = new MyUserDetails(user);
        when(userDetailsService.loadUserByUsername(Mockito.<String>any())).thenReturn(myUserDetails);
        when(jwtService.validateToken(Mockito.<String>any(), Mockito.<UserDetails>any())).thenReturn(true);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getSession(anyBoolean())).thenReturn(new MockHttpSession());
        when(request.getRemoteAddr()).thenReturn("42 Main St");
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
        verify(request).getRemoteAddr();
        verify(request).getHeader(eq("Authorization"));
        verify(request).getSession(eq(false));
        verify(jwtService).extractUsername(eq(""));
        verify(jwtService).validateToken(eq(""), isA(UserDetails.class));
        verify(userDetailsService).loadUserByUsername(eq("janedoe"));
    }

    /**
     * Method under test:
     * {@link JwtRequestFilter#doFilterInternal(HttpServletRequest, HttpServletResponse, FilterChain)}
     */
    @Test
    void testDoFilterInternal4() throws ServletException, IOException, UsernameNotFoundException {
        // Arrange
        UserEmployee user = new UserEmployee();
        user.setRoles(new ArrayList<>());
        MyUserDetails myUserDetails = new MyUserDetails(user);
        when(userDetailsService.loadUserByUsername(Mockito.<String>any())).thenReturn(myUserDetails);
        when(jwtService.validateToken(Mockito.<String>any(), Mockito.<UserDetails>any())).thenReturn(false);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        Response response = new Response();
        FilterChain filterChain = mock(FilterChain.class);
        doNothing().when(filterChain).doFilter(Mockito.<ServletRequest>any(), Mockito.<ServletResponse>any());

        // Act
        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(filterChain).doFilter(isA(ServletRequest.class), isA(ServletResponse.class));
        verify(request).getHeader(eq("Authorization"));
        verify(jwtService).extractUsername(eq(""));
        verify(jwtService).validateToken(eq(""), isA(UserDetails.class));
        verify(userDetailsService).loadUserByUsername(eq("janedoe"));
    }
}
