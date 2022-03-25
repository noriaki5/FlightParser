package com.study.noriaki.FlightParser.calculators;

import com.study.noriaki.FlightParser.Ticket;

import java.time.Duration;
import java.util.List;

public class AverageFlightTimeCalculator implements Calculator<Duration> {
    private String departure;
    private String destination;

    public AverageFlightTimeCalculator(String departure, String destination) {
        this.departure = departure;
        this.destination = destination;
    }

    @Override
    public CalculatorResult<Duration> calculate(List<Ticket> tickets) {
        double avgMinutes = tickets.stream()
            .filter(x -> x.getDepartureAirportName().equals(departure))
            .filter(x -> x.getDestinationAirportName().equals(destination))
            .mapToLong(x -> Duration.between(x.getDepartureDate().atTime(x.getDepartureTime()),
                x.getArrivalDate().atTime(x.getArrivalTime())).toMinutes())
            .average()
            .orElse(Double.NaN);
        Duration duration = Duration.ofMinutes((long)avgMinutes);
        CalculatorResult<Duration> durationCalculatorResult = new CalculatorResult<>();
        durationCalculatorResult.setResult(duration);
        durationCalculatorResult.setResultInfo(String.format("Average flight time between %s and %s is %d days %d hours %d minutes",
            departure, destination, duration.toDaysPart(), duration.toHoursPart(), duration.toMinutesPart()));

        return durationCalculatorResult;
    }
}