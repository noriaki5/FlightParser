package com.study.noriaki.FlightParser;

import com.study.noriaki.FlightParser.calculators.*;

public class DurationTemplate {
    private String template;
    private String departure;
    private String destination;
    private double percentile = 0;

    public DurationTemplate(Calculator<?> calculator) {
        setDeparture(calculator);
        setDestination(calculator);
        setPercentile(calculator);
        setCalculatorTemplate(calculator);
    }

    public String getTemplate() {
        return template;
    }

    private void setDeparture(Calculator<?> calculator) {
        if (calculator instanceof AverageFlightTimeCalculator) {
            departure = ((AverageFlightTimeCalculator) calculator).getDeparture();
        } else if (calculator instanceof AverageFlightTimeCalculatorWithTimezone) {
            departure = ((AverageFlightTimeCalculatorWithTimezone) calculator).getDeparture();
        } else if (calculator instanceof PercentileFlightTime) {
            departure = ((PercentileFlightTime) calculator).getDeparture();
        } else if (calculator instanceof PercentileFlightTimeWithTimeZones) {
            departure = ((PercentileFlightTimeWithTimeZones) calculator).getDeparture();
        }
    }

    private void setDestination(Calculator<?> calculator) {
        if (calculator instanceof AverageFlightTimeCalculator) {
            destination = ((AverageFlightTimeCalculator) calculator).getDestination();
        } else if (calculator instanceof AverageFlightTimeCalculatorWithTimezone) {
            destination = ((AverageFlightTimeCalculatorWithTimezone) calculator).getDestination();
        } else if (calculator instanceof PercentileFlightTime) {
            destination = ((PercentileFlightTime) calculator).getDestination();
        } else if (calculator instanceof PercentileFlightTimeWithTimeZones) {
            destination = ((PercentileFlightTimeWithTimeZones) calculator).getDestination();
        }
    }

    private void setPercentile(Calculator<?> calculator) {
        if (calculator instanceof PercentileFlightTime) {
            percentile = ((PercentileFlightTime) calculator).getPercentile();
        } else if (calculator instanceof PercentileFlightTimeWithTimeZones) {
            percentile = ((PercentileFlightTimeWithTimeZones) calculator).getPercentile();
        }
    }

    private void setCalculatorTemplate(Calculator<?> calculator) {
        if (calculator instanceof AverageFlightTimeCalculator) {
            template = String.format("Average flight time between %s and %s is ", departure, destination)
                + "%d days %d hours %d minutes";
        } else if (calculator instanceof AverageFlightTimeCalculatorWithTimezone) {
            template = String.format("Average flight time between %s and %s with timezone changes is ",
                departure, destination) + "%d days %d hours %d minutes";
        } else if (calculator instanceof PercentileFlightTime) {
            template = String.format("%.2f percentile flight time between %s and %s is ",
                percentile, departure, destination) + "%d days %d hours %d minutes";
        } else if (calculator instanceof PercentileFlightTimeWithTimeZones) {
            template = String.format("%.2f percentile flight time between %s and %s with timezone changes is ",
                percentile, departure, destination) + "%d days %d hours %d minutes";
        }
    }
}