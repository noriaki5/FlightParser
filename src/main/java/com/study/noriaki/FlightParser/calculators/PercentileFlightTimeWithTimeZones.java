package com.study.noriaki.FlightParser.calculators;

import com.study.noriaki.FlightParser.Ticket;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PercentileFlightTimeWithTimeZones implements Calculator<Duration> {
    private String departure;
    private String destination;
    private double percentile;

    public PercentileFlightTimeWithTimeZones(String departure, String destination, double percentile) {
        this.departure = departure;
        this.destination = destination;
        this.percentile = percentile;
    }

    @Override
    public CalculatorResult<Duration> calculate(List<Ticket> tickets) {
        List<Long> sortedTickets = tickets.stream()
            .filter(x -> x.getDepartureAirportName().equals(departure))
            .filter(x -> x.getDestinationAirportName().equals(destination))
            .mapToLong(x -> Duration.between(x.getDepartureDateTime(),
                x.getArrivalDateTime()).toMinutes())
            .sorted()
            .boxed()
            .collect(Collectors.toList());
        Duration duration = Duration.ofMinutes(percentile(sortedTickets, percentile));
        CalculatorResult<Duration> durationCalculatorResult = new CalculatorResult<>();
        durationCalculatorResult.setResult(duration);

        return durationCalculatorResult;
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
}