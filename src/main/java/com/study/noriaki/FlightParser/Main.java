package com.study.noriaki.FlightParser;

import com.study.noriaki.FlightParser.calculators.*;
import com.study.noriaki.FlightParser.printers.Printer;

import java.io.File;

public class Main {
    private static final String DEFAULT_PATH = "src/main/resources/tickets.json";
    private static final Printer PRINTER = new Printer();
    private static final String VLADIVOSTOK = "Владивосток";
    private static final String TEL_AVIV = "Тель-Авив";

    public static void main(String[] args) {
        Tickets tickets = args.length > 0 ? parse(args[0]) : parse(DEFAULT_PATH);
        Calculator avgFlightTimeCalculator = new AverageFlightTimeCalculator(VLADIVOSTOK, TEL_AVIV);
        PRINTER.print(new DurationTemplate(avgFlightTimeCalculator, VLADIVOSTOK, TEL_AVIV),
            avgFlightTimeCalculator.calculate(tickets.getTickets()));

        Calculator avgFlightTimeCalcWithTimezone = new AverageFlightTimeCalculatorWithTimezone(VLADIVOSTOK, TEL_AVIV);
        PRINTER.print(new DurationTemplate(avgFlightTimeCalcWithTimezone, VLADIVOSTOK, TEL_AVIV),
            avgFlightTimeCalcWithTimezone.calculate(tickets.getTickets()));

        Calculator percentileFlightTime = new PercentileFlightTime(VLADIVOSTOK, TEL_AVIV, 90);
        PRINTER.print(new DurationTemplate(percentileFlightTime, VLADIVOSTOK, TEL_AVIV, 90),
            percentileFlightTime.calculate(tickets.getTickets()));

        Calculator percentileFlightTimeWithTimeZones = new PercentileFlightTimeWithTimeZones(VLADIVOSTOK, TEL_AVIV, 90);
        PRINTER.print(new DurationTemplate(percentileFlightTimeWithTimeZones, VLADIVOSTOK, TEL_AVIV, 90),
            percentileFlightTimeWithTimeZones.calculate(tickets.getTickets()));
    }

    public static Tickets parse(String filePath) {
        TicketParser ticketParser = new TicketParser();

        return ticketParser.parseJSON(new File(filePath));
    }
}