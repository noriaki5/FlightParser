package com.study.noriaki.FlightParser.calculators;

import com.study.noriaki.FlightParser.Ticket;

import java.util.List;

public interface Calculator<T> {

    CalculatorResult<T> calculate(List<Ticket> tickets);
}