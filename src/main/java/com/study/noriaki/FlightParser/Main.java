package com.study.noriaki.FlightParser;

import java.io.File;

public class Main {
    final static String defaultPath = "src/main/resources/tickets.json";

    public static void main(String[] args) {

        String filePath;
        if (args.length > 0) {
            filePath = args[0];
        } else {
            filePath = defaultPath;
        }
        TicketParser ticketParser = new TicketParser();
        Tickets tickets = ticketParser.parseJSON(new File(filePath));
        //tickets.getTickets().forEach(System.out::println);

        TicketsCalculations ticketsCalculations = new TicketsCalculations();
        //System.out.println(ticketsCalculations.calculateAveragePrice(tickets.getTickets(), "Владивосток", "Тель-Авив"));
        ticketsCalculations.calculateAverageFlightTime(tickets.getTickets(), "Владивосток", "Тель-Авив");
        ticketsCalculations.calculateAverageFlightTimeWithTimeZones(tickets.getTickets(), "Владивосток", "Тель-Авив");
        ticketsCalculations.calculatePercentileFlightTime(tickets.getTickets(), "Владивосток", "Тель-Авив", 90.0);
        ticketsCalculations.calculatePercentileFlightTimeWithTimeZones(tickets.getTickets(), "Владивосток", "Тель-Авив", 90.0);
    }
}