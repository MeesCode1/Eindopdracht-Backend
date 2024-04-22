package nl.novi.eindopdrachtBackenSystemGoldencarrot.generalMethodsComponent;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class SetTimeAndDate {

    public static Order SetOrderDateAndTime(Order order) {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentTimeHHmmSS = currentTime.format(formatter);

        order.setOrderDate(currentDate);
        order.setOrderTime(currentTimeHHmmSS);
        return order;
    }
}
