import static org.junit.jupiter.api.Assertions.assertEquals;

import com.study.noriaki.FlightParser.Ticket;
import com.study.noriaki.FlightParser.calculators.PercentileFlightTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PercentileFlightTimeTest {
    private PercentileFlightTime percentileFlightTime;
    private static final String VLADIVOSTOK = "Владивосток";
    private static final String TEL_AVIV = "Тель-Авив";

    @BeforeEach
    void prepare() {
        percentileFlightTime = new PercentileFlightTime(VLADIVOSTOK, TEL_AVIV, 90);
    }

    @Test
    void calculateShouldReturn1Day1HourDurationWhen1Day1HourDurationProceeded() {
        List<Ticket> list = new ArrayList<>();
        Ticket ticket = new Ticket();
        ticket.setDepartureAirportName(VLADIVOSTOK);
        ticket.setDestinationAirportName(TEL_AVIV);
        ticket.setDepartureDate(LocalDate.of(2020, 3, 21));
        ticket.setDepartureTime(LocalTime.of(10,0));
        ticket.setArrivalDate(LocalDate.of(2020, 3, 22));
        ticket.setArrivalTime(LocalTime.of(11,0));
        list.add(ticket);
        Duration expected = Duration.ofDays(1).plusHours(1);
        Duration actual = percentileFlightTime.calculate(list);

        assertEquals(expected, actual);
    }
}