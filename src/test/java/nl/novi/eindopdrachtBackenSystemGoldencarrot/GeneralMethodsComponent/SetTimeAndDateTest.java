package nl.novi.eindopdrachtBackenSystemGoldencarrot.GeneralMethodsComponent;

import nl.novi.eindopdrachtBackenSystemGoldencarrot.generalMethodsComponent.SetTimeAndDate;
import nl.novi.eindopdrachtBackenSystemGoldencarrot.models.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetTimeAndDateTest {

    @Test
    void shouldReturnOrderWithTimeAndDate() {

        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentTimeHHmmSS = currentTime.format(formatter);

        Order result = new Order();

        result = SetTimeAndDate.SetOrderDateAndTime(result);

        assertEquals(currentDate, result.getOrderDate());
        assertEquals(currentTimeHHmmSS, result.getOrderTime());


    }
}
