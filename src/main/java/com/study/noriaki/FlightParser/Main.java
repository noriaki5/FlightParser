package com.study.noriaki.FlightParser;

import com.study.noriaki.FlightParser.calculators.*;
import com.study.noriaki.FlightParser.printers.Printer;

import java.io.File;
import java.util.List;

public class Main {
    private static final String DEFAULT_PATH = "src/main/resources/tickets.json";
    private static final Printer PRINTER = new Printer();

    static List<Calculator<?>> calculators = List.of(
        new AverageFlightTimeCalculator("Владивосток", "Тель-Авив"),
        new AverageFlightTimeCalculatorWithTimezone("Владивосток", "Тель-Авив"),
        new PercentileFlightTime("Владивосток", "Тель-Авив", 90.0),
        new PercentileFlightTimeWithTimeZones("Владивосток", "Тель-Авив", 90.0)
    );

    public static void main(String[] args) {
        Tickets tickets = args.length > 0 ? parse(args[0]) : parse(DEFAULT_PATH);
        calculators.forEach(calc -> PRINTER.print(new DurationTemplate(calc), calc.calculate(tickets.getTickets()).getResult()));
    }

    public static Tickets parse(String filePath) {
        TicketParser ticketParser = new TicketParser();

        return ticketParser.parseJSON(new File(filePath));
    }
}