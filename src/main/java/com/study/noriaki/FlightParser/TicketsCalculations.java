package com.study.noriaki.FlightParser;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TicketsCalculations {
    public Double calculateAveragePrice(List<Ticket> tickets, String departure, String destination) {
        return tickets.stream()
                .filter(x -> x.getDepartureAirportName().equals(departure))
                .filter(x -> x.getDestinationAirportName().equals(destination))
                .mapToDouble(x -> x.getPrice().doubleValue()).average().orElse(Double.NaN);
    }

    public void calculateAverageFlightTime(List<Ticket> tickets, String departure, String destination) {

        double avgMinutes = tickets.stream()
                .filter(x -> x.getDepartureAirportName().equals(departure))
                .filter(x -> x.getDestinationAirportName().equals(destination))
                .mapToLong(x -> Duration.between(x.getDepartureDate().atTime(x.getDepartureTime()),
                            x.getArrivalDate().atTime(x.getArrivalTime())).toMinutes())
                .average()
                .orElse(Double.NaN);
        Duration duration = Duration.ofMinutes((long)avgMinutes);
        System.out.printf(
                "Average flight time between %s and %s is %d days %d hours %d minutes\n",
                departure, destination, duration.toDaysPart(), duration.toHoursPart(), duration.toMinutesPart()
        );
    }

    public void calculateAverageFlightTimeWithTimeZones(List<Ticket> tickets, String departure, String destination) {

        double avgMinutes = tickets.stream()
                .filter(x -> x.getDepartureAirportName().equals(departure))
                .filter(x -> x.getDestinationAirportName().equals(destination))
                .mapToLong(x -> Duration.between(x.getDepartureDateTime(),
                        x.getArrivalDateTime()).toMinutes())
                .average()
                .orElse(Double.NaN);
        Duration duration = Duration.ofMinutes((long)avgMinutes);
        System.out.printf(
                "Average flight time between %s and %s with timezone changes is %d days %d hours %d minutes\n",
                departure, destination, duration.toDaysPart(), duration.toHoursPart(), duration.toMinutesPart()
        );
    }

    public void calculatePercentileFlightTime(List<Ticket> tickets, String departure, String destination, double percentile) {
        List<Long> sortedTickets = tickets.stream()
                .filter(x -> x.getDepartureAirportName().equals(departure))
                .filter(x -> x.getDestinationAirportName().equals(destination))
                .mapToLong(x -> Duration.between(x.getDepartureDate().atTime(x.getDepartureTime()),
                        x.getArrivalDate().atTime(x.getArrivalTime())).toMinutes())
                .sorted()
                .boxed()
                .collect(Collectors.toList());
        Duration duration = Duration.ofMinutes(percentile(sortedTickets, percentile));
        System.out.printf(
                "%.2f percentile flight time between %s and %s is %d days %d hours %d minutes\n",
                percentile, departure, destination, duration.toDaysPart(), duration.toHoursPart(), duration.toMinutesPart()
        );
    }

    public void calculatePercentileFlightTimeWithTimeZones(List<Ticket> tickets, String departure, String destination, double percentile) {
        List<Long> sortedTickets = tickets.stream()
                .filter(x -> x.getDepartureAirportName().equals(departure))
                .filter(x -> x.getDestinationAirportName().equals(destination))
                .mapToLong(x -> Duration.between(x.getDepartureDateTime(),
                        x.getArrivalDateTime()).toMinutes())
                .sorted()
                .boxed()
                .collect(Collectors.toList());
        Duration duration = Duration.ofMinutes(percentile(sortedTickets, percentile));
        System.out.printf(
                "%.2f percentile flight time between %s and %s with timezone changes is %d days %d hours %d minutes\n",
                percentile, departure, destination, duration.toDaysPart(), duration.toHoursPart(), duration.toMinutesPart()
        );
    }

    public long percentile(List<Long> list, double percentile) {
        Collections.sort(list);
        int index = (int) Math.ceil(percentile / 100.0 * list.size());
        if (index - 1 < 0) {
            return 0;
        } else {
            return list.get(index - 1);
        }
    }
}