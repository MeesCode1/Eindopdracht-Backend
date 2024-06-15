package nl.novi.eindopdrachtBackenSystemGoldencarrot.utilsGeneralMethods.emailSending;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmailSender.class})
@ExtendWith(SpringExtension.class)
class EmailSenderDiffblueTest {
    @Autowired
    private EmailSender emailSender;


}
