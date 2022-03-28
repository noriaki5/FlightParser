package com.study.noriaki.FlightParser.calculators;

import com.study.noriaki.FlightParser.Ticket;

import java.time.Duration;
import java.util.List;

public interface Calculator {

    Duration calculate(List<Ticket> tickets);
}