package nl.novi.eindopdrachtBackenSystemGoldencarrot.security;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JwtService.class})
@ExtendWith(SpringExtension.class)
class JwtServiceDiffblueTest {
    @Autowired
    private JwtService jwtService;


}
