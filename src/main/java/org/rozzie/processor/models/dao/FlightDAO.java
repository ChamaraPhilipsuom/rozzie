package org.rozzie.processor.models.dao;

import org.rozzie.processor.models.Flight;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Table
public class FlightDAO {

    @PrimaryKey
    private UUID flightID;
    private String plannedDepatureTime;
    private String plannedArrivalTime;
    private String actualDepatureTime;
    private String actualArrivalTime;
    private String source;
    private String destination;

    public FlightDAO(){}
    public FlightDAO(UUID flightID, LocalDateTime plannedArrivalTime, LocalDateTime plannedDepatureTime, LocalDateTime
            actualArrivalTime, LocalDateTime actualDepatureTime, String source, String destination) {
        this.flightID = flightID;
        this.plannedArrivalTime = plannedArrivalTime.toString();
        this.plannedDepatureTime = plannedDepatureTime.toString();
        this.actualArrivalTime = actualArrivalTime.toString();
        this.actualDepatureTime = actualDepatureTime.toString();
        this.source = source;
        this.destination = destination;
    }

	public FlightDAO(Flight flight) {
		this.flightID = flight.getFlightID();
		this.plannedArrivalTime = flight.getPlannedArrivalTime().toString();
		this.plannedDepatureTime = flight.getPlannedDepatureTime().toString();
		this.actualArrivalTime = flight.getActualArrivalTime().toString();
		this.actualDepatureTime = flight.getActualDepatureTime().toString();
		this.source = flight.getSource().toString();
		this.destination = flight.getDestination().toString();
	}

    public LocalDateTime getPlannedDepatureTime() {
        return LocalDateTime.parse(plannedDepatureTime);
    }

    public void setPlannedDepatureTime(LocalDateTime plannedDepatureTime) {
        this.plannedDepatureTime = plannedDepatureTime.toString();
    }

    public LocalDateTime getPlannedArrivalTime() {
        return LocalDateTime.parse(plannedArrivalTime);
    }

    public void setPlannedArrivalTime(LocalDateTime plannedArrivalTime) {
        this.plannedArrivalTime = plannedArrivalTime.toString();
    }

    public LocalDateTime getActualDepatureTime() {
        return LocalDateTime.parse(actualDepatureTime);
    }

    public void setActualDepatureTime(LocalDateTime actualDepatureTime) {
        this.actualDepatureTime = actualDepatureTime.toString();
    }

    public LocalDateTime getActualArrivalTime() {
        return LocalDateTime.parse(actualArrivalTime);
    }

    public void setActualArrivalTime(LocalDateTime actualArrivalTime) {
        this.actualArrivalTime = actualArrivalTime.toString();
    }

    public UUID getFlightID(){
        return this.flightID;
    }

    public void setFlightID(UUID flightID) {
        this.flightID = flightID;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
