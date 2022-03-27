package com.study.noriaki.FlightParser.calculators;

import com.study.noriaki.FlightParser.Ticket;

import java.time.Duration;
import java.util.List;

public class AverageFlightTimeCalculatorWithTimezone implements Calculator {
    private final String departure;
    private final String destination;

    public AverageFlightTimeCalculatorWithTimezone(String departure, String destination) {
        this.departure = departure;
        this.destination = destination;
    }
    @Override
    public Duration calculate(List<Ticket> tickets) {
        double avgMinutes = tickets.stream()
            .filter(x -> x.getDepartureAirportName().equals(departure))
            .filter(x -> x.getDestinationAirportName().equals(destination))
            .mapToLong(x -> Duration.between(x.getDepartureDateTime(),
                x.getArrivalDateTime()).toMinutes())
            .average()
            .orElse(Double.NaN);

        return Duration.ofMinutes((long)avgMinutes);
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }
}