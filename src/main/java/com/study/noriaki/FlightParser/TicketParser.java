package com.study.noriaki.FlightParser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class TicketParser {
    private final ObjectMapper mapper;

    public TicketParser() {
        this.mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    }

    public Tickets parseJSON(File file) {
        Tickets tickets = readJSON(file);
        processTickets(tickets);

        return tickets;
    }

    public Tickets parseJSON(String json) {
        Tickets tickets = readJSON(json);
        processTickets(tickets);

        return tickets;
    }

    public Tickets parseJSON(InputStream inputStream) {
        Tickets tickets = readJSON(inputStream);
        processTickets(tickets);

        return tickets;
    }

    public Tickets parseJSON(URL url) {
        Tickets tickets = readJSON(url);
        processTickets(tickets);

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

    private Tickets readJSON(File file) {
        try {
            return mapper.readValue(file, Tickets.class);
        } catch (Exception e) {
            throw new JSONParseException(e.getMessage());
        }
    }

    private Tickets readJSON(String json) {
        try {
            return mapper.readValue(json, Tickets.class);
        } catch (Exception e) {
            throw new JSONParseException(e.getMessage());
        }
    }

    private Tickets readJSON(InputStream inputStream) {
        try {
            return mapper.readValue(inputStream, Tickets.class);
        } catch (Exception e) {
            throw new JSONParseException(e.getMessage());
        }
    }

    private Tickets readJSON(URL url) {
        try {
            return mapper.readValue(url, Tickets.class);
        } catch (Exception e) {
            throw new JSONParseException(e.getMessage());
        }
    }

    private void processTickets(Tickets tickets) {
        setZonedTime(tickets.getTickets());
    }

    private void setZonedTime(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            ticket.setDepartureDateTime(
                ZonedDateTime.of(ticket.getDepartureDate(),
                    ticket.getDepartureTime(),
                    getZoneIdByAirportName(ticket.getDepartureAirportName())));
            ticket.setArrivalDateTime(
                ZonedDateTime.of(ticket.getArrivalDate(),
                    ticket.getArrivalTime(),
                    getZoneIdByAirportName(ticket.getDestinationAirportName())));
        }
    }
}