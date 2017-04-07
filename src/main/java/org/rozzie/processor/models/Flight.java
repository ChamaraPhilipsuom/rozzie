package org.rozzie.processor.models;

import org.rozzie.processor.events.FlightDepatureTimeChangeEvent;
import org.rozzie.processor.listeners.AirlineListener;
import org.rozzie.processor.listeners.FlightDepartureEventListener;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;

/**
 * Created by chamarap on 4/6/17.
 */
public class Flight extends EventSource{

	private UUID flightID;
	private LocalDateTime plannedDepatureTime;
	private LocalDateTime plannedArrivalTime;
	private LocalDateTime actualDepatureTime;
	private LocalDateTime actualArrivalTime;
	
	public Flight(UUID flightID, LocalDateTime plannedArrivalTime, LocalDateTime plannedDepatureTime, LocalDateTime actualArrivalTime,
				  LocalDateTime actualDepatureTime) {
		this.flightID = flightID;
		this.plannedArrivalTime = plannedArrivalTime;
		this.plannedDepatureTime = plannedDepatureTime;
		this.actualArrivalTime = actualArrivalTime;
		this.actualDepatureTime = actualDepatureTime;
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

	public boolean isLate(LocalDateTime init, LocalDateTime end){
		return Duration.between(init,end).isNegative();
	}

	private synchronized void fireDepatureTimeChangeEvent() {
		FlightDepatureTimeChangeEvent event = new FlightDepatureTimeChangeEvent(this);
		for (AirlineListener listener : getListeners()) {
			((FlightDepartureEventListener) listener).changeReceived(event);
		}
	}
}
