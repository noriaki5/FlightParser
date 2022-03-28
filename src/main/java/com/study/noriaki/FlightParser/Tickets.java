package com.study.noriaki.FlightParser;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName("tickets")
public class Tickets {
    private List<Ticket> tickets;

    public Tickets() {

    }

    public Tickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}