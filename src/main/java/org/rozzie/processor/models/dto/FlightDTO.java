package org.rozzie.processor.models.dto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;


public class FlightDTO {
    private UUID flightID;
    private LocalDateTime plannedDepatureTime;
    private LocalDateTime plannedArrivalTime;
    private LocalDateTime actualDepatureTime;
    private LocalDateTime actualArrivalTime;
    private AirportDTO source;
    private AirportDTO destination;

    public FlightDTO(){}

    public FlightDTO(UUID flightID, LocalDateTime plannedArrivalTime, LocalDateTime plannedDepatureTime, LocalDateTime actualArrivalTime,
                     LocalDateTime actualDepatureTime, AirportDTO source, AirportDTO destination) {
        this.flightID = flightID;
        this.plannedArrivalTime = plannedArrivalTime;
        this.plannedDepatureTime = plannedDepatureTime;
        this.actualArrivalTime = actualArrivalTime;
        this.actualDepatureTime = actualDepatureTime;
        this.source = source;
        this.destination = destination;
    }

    public LocalDateTime getPlannedDepatureTime() {
        return plannedDepatureTime;
    }

    public void setPlannedDepatureTime(LocalDateTime plannedDepatureTime) {
        this.plannedDepatureTime = plannedDepatureTime;
    }

    public LocalDateTime getPlannedArrivalTime() {
        return plannedArrivalTime;
    }

    public void setPlannedArrivalTime(LocalDateTime plannedArrivalTime) {
        this.plannedArrivalTime = plannedArrivalTime;
    }

    public LocalDateTime getActualDepatureTime() {
        return actualDepatureTime;
    }

    public void setActualDepatureTime(LocalDateTime actualDepatureTime) {
        this.actualDepatureTime = actualDepatureTime;
    }

    public LocalDateTime getActualArrivalTime() {
        return actualArrivalTime;
    }

    public void setActualArrivalTime(LocalDateTime actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime;
    }

    public UUID getFlightID(){
        return this.flightID;
    }

    public void setFlightID(UUID flightID) {
        this.flightID = flightID;
    }

    public AirportDTO getSource() {
        return source;
    }

    public void setSource(AirportDTO source) {
        this.source = source;
    }

    public AirportDTO getDestination() {
        return destination;
    }

    public void setDestination(AirportDTO destination) {
        this.destination = destination;
    }

}
