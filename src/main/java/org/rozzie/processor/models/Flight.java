package org.rozzie.processor.models;

import org.neo4j.ogm.annotation.NodeEntity;
import org.rozzie.processor.events.AirlineEvent;
import org.rozzie.processor.events.FlightDepatureTimeChangeEvent;
import org.rozzie.processor.listeners.AirlineListener;
import org.rozzie.processor.listeners.FlightDepartureEventListener;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class Flight extends EventSource{


	private UUID flightID;
	private LocalDateTime plannedDepatureTime;
	private LocalDateTime plannedArrivalTime;
	private LocalDateTime actualDepatureTime;
	private LocalDateTime actualArrivalTime;
	private Airport source;
	private Airport destination;

	public Flight(){}

	public Flight(UUID flightID, LocalDateTime plannedArrivalTime, LocalDateTime plannedDepatureTime, LocalDateTime actualArrivalTime,
				  LocalDateTime actualDepatureTime, Airport source, Airport destination) {
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
		fireDepatureTimeChangeEvent();
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

	public boolean isLate(LocalDateTime init, LocalDateTime end){
		return Duration.between(init,end).isNegative();
	}

	public Airport getSource() {
		return source;
	}

	public void setSource(Airport source) {
		this.source = source;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}

	private synchronized void fireDepatureTimeChangeEvent() {
		AirlineEvent event = new FlightDepatureTimeChangeEvent(this);
		for (AirlineListener listener : getListeners()) {
			listener.changeReceived(event);
		}
	}
}
