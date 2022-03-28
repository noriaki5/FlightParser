import com.study.noriaki.FlightParser.Ticket;
import com.study.noriaki.FlightParser.TicketParser;
import com.study.noriaki.FlightParser.Tickets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketParserTest {
    private TicketParser ticketParser;

    @BeforeEach
    void prepare() {
        ticketParser = new TicketParser();
    }

    @Test
    void parseJSONShouldReturnCorrectSizeOfParsedListWhenParseTestFile() {
        List<Ticket> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Ticket());
        }
        int expected = list.size();

        Tickets tickets = ticketParser.parseJSON(new File("src/main/resources/tickets.json"));
        int actual = tickets.getTickets().size();

        assertEquals(expected, actual);
    }
}