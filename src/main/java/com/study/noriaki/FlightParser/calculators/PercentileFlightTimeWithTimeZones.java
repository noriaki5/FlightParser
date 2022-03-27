package com.study.noriaki.FlightParser.calculators;

import com.study.noriaki.FlightParser.Ticket;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PercentileFlightTimeWithTimeZones implements Calculator {
    private final String departure;
    private final String destination;
    private final double percentile;

    public PercentileFlightTimeWithTimeZones(String departure, String destination, double percentile) {
        this.departure = departure;
        this.destination = destination;
        this.percentile = percentile;
    }

    @Override
    public Duration calculate(List<Ticket> tickets) {
        List<Long> sortedTickets = tickets.stream()
            .filter(x -> x.getDepartureAirportName().equals(departure))
            .filter(x -> x.getDestinationAirportName().equals(destination))
            .mapToLong(x -> Duration.between(x.getDepartureDateTime(),
                x.getArrivalDateTime()).toMinutes())
            .sorted()
            .boxed()
            .collect(Collectors.toList());

        return Duration.ofMinutes(percentile(sortedTickets, percentile));
    }

    private long percentile(List<Long> list, double percentile) {
        Collections.sort(list);
        int index = (int) Math.ceil(percentile / 100.0 * list.size());
        if (index - 1 < 0) {
            return 0;
        } else {
            return list.get(index - 1);
        }
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public double getPercentile() {
        return percentile;
    }
}