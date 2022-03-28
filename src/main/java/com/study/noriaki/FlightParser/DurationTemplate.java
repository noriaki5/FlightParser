package com.study.noriaki.FlightParser;

import com.study.noriaki.FlightParser.calculators.*;

public class DurationTemplate {
    private String template;
    private final String departure;
    private final String destination;
    private double percentile = 0;

    public DurationTemplate(Calculator calculator, String departure, String destination) {
        this.departure = departure;
        this.destination = destination;
        setCalculatorTemplate(calculator);
    }

    public DurationTemplate(Calculator calculator, String departure, String destination, double percentile) {
        this.departure = departure;
        this.destination = destination;
        this.percentile = percentile;
        setCalculatorTemplate(calculator);
    }

    public String getTemplate() {
        return template;
    }

    private void setCalculatorTemplate(Calculator calculator) {
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
        } else {
            template = "Duration is %d days %d hours %d minutes";
        }
    }
}