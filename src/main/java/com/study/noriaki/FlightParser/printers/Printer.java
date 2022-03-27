package com.study.noriaki.FlightParser.printers;

import com.study.noriaki.FlightParser.DurationTemplate;

import java.time.Duration;

public class Printer {
    public void print(DurationTemplate template, Object t) {
        System.out.printf((template.getTemplate()) + "%n", ((Duration)t).toDaysPart(), ((Duration)t).toHoursPart(), ((Duration)t).toMinutesPart());
    }
}