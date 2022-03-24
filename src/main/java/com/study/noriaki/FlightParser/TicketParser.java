package com.study.noriaki.FlightParser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TicketParser {
    public Tickets parseJSON(File file) {
        Tickets tickets = new Tickets();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        try {
            tickets = mapper.readValue(file, Tickets.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Ticket ticket : tickets.getTickets()) {
            ticket.setDepartureDateTime(ZonedDateTime.of(ticket.getDepartureDate(), ticket.getDepartureTime(), getZoneIdByAirportName(ticket.getDepartureAirportName())));
            ticket.setArrivalDateTime(ZonedDateTime.of(ticket.getArrivalDate(), ticket.getArrivalTime(), getZoneIdByAirportName(ticket.getDestinationAirportName())));
        }

        return tickets;
    }

    public Tickets parseJSON(String jsonString) {
        Tickets tickets = new Tickets();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        try {
            tickets = mapper.readValue(jsonString, Tickets.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Ticket ticket : tickets.getTickets()) {
            ticket.setDepartureDateTime(ZonedDateTime.of(ticket.getDepartureDate(), ticket.getDepartureTime(), getZoneIdByAirportName(ticket.getDepartureAirportName())));
            ticket.setArrivalDateTime(ZonedDateTime.of(ticket.getArrivalDate(), ticket.getArrivalTime(), getZoneIdByAirportName(ticket.getDestinationAirportName())));
        }

        return tickets;
    }

    public ZoneId getZoneIdByAirportName(String name) {
        if (name.equals("Владивосток")) {
            return ZoneId.of("Asia/Vladivostok");
        } else if (name.equals("Тель-Авив")) {
            return ZoneId.of("Asia/Tel_Aviv");
        } else {
            return ZoneId.systemDefault();
        }
    }
}
