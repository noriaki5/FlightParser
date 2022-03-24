package com.study.noriaki.FlightParser;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;

public class Ticket {
    @JsonProperty("origin")
    private String departureAirportId;

    @JsonProperty("origin_name")
    private String departureAirportName;

    @JsonProperty("destination")
    private String destinationAirportId;

    @JsonProperty("destination_name")
    private String destinationAirportName;

    @JsonProperty("departure_date")
    @JsonFormat(pattern = "dd.MM.yy")
    private LocalDate departureDate;

    @JsonProperty("departure_time")
    @JsonFormat(pattern = "H:mm")
    private LocalTime departureTime;

    @JsonProperty("arrival_date")
    @JsonFormat(pattern = "dd.MM.yy")
    private LocalDate arrivalDate;

    @JsonProperty("arrival_time")
    @JsonFormat(pattern = "H:mm")
    private LocalTime arrivalTime;

    private ZonedDateTime departureDateTime;
    private ZonedDateTime arrivalDateTime;
    private String carrier;
    private int stops;
    private BigInteger price;

    public Ticket() {

    }

    public Ticket(String departureAirportId, String departureAirportName, String destinationAirportId, String destinationAirportName, LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime, ZonedDateTime departureDateTime, ZonedDateTime arrivalDateTime, String carrier, int stops, BigInteger price) {
        this.departureAirportId = departureAirportId;
        this.departureAirportName = departureAirportName;
        this.destinationAirportId = destinationAirportId;
        this.destinationAirportName = destinationAirportName;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.carrier = carrier;
        this.stops = stops;
        this.price = price;
    }

    public String getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(String departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public String getDestinationAirportId() {
        return destinationAirportId;
    }

    public void setDestinationAirportId(String destinationAirportId) {
        this.destinationAirportId = destinationAirportId;
    }

    public String getDestinationAirportName() {
        return destinationAirportName;
    }

    public void setDestinationAirportName(String destinationAirportName) {
        this.destinationAirportName = destinationAirportName;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public ZonedDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(ZonedDateTime departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public ZonedDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(ZonedDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "departureAirportId='" + departureAirportId + '\'' +
                ", departureAirportName='" + departureAirportName + '\'' +
                ", destinationAirportId='" + destinationAirportId + '\'' +
                ", destinationAirportName='" + destinationAirportName + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", arrivalDate=" + arrivalDate +
                ", arrivalTime=" + arrivalTime +
                ", departureDateTime=" + departureDateTime +
                ", arrivalDateTime=" + arrivalDateTime +
                ", carrier='" + carrier + '\'' +
                ", stops=" + stops +
                ", price=" + price +
                '}';
    }
}
