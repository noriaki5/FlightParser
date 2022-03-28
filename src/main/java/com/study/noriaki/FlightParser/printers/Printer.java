package com.study.noriaki.FlightParser.printers;

import com.study.noriaki.FlightParser.DurationTemplate;

import java.time.Duration;

public class Printer {
    public void print(DurationTemplate template, Duration t) {
        System.out.printf((template.getTemplate()) + "%n", t.toDaysPart(), t.toHoursPart(), t.toMinutesPart());
    }
}