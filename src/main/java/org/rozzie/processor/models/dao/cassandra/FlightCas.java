package org.rozzie.processor.models.dao.cassandra;

import org.rozzie.processor.models.Flight;
import org.rozzie.processor.models.dto.BaseDTO;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Table
public class FlightCas implements BaseCas {

	@PrimaryKey
	private UUID flightID;
	private String plannedDepatureTime;
	private String plannedArrivalTime;
	private String actualDepatureTime;
	private String actualArrivalTime;

	public FlightCas() {
	}
	public FlightCas(UUID flightID, LocalDateTime plannedArrivalTime, LocalDateTime plannedDepatureTime,
			LocalDateTime actualArrivalTime, LocalDateTime actualDepatureTime) {
		this.flightID = flightID;
		this.plannedArrivalTime = plannedArrivalTime.toString();
		this.plannedDepatureTime = plannedDepatureTime.toString();
		this.actualArrivalTime = actualArrivalTime.toString();
		this.actualDepatureTime = actualDepatureTime.toString();
	}

	public FlightCas(Flight flight) {
		this.flightID = flight.getFlightID();
		this.plannedArrivalTime = flight.getPlannedArrivalTime().toString();
		this.plannedDepatureTime = flight.getPlannedDepatureTime().toString();
		this.actualArrivalTime = flight.getActualArrivalTime().toString();
		this.actualDepatureTime = flight.getActualDepatureTime().toString();
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

	public UUID getFlightID() {
		return this.flightID;
	}

	public void setFlightID(UUID flightID) {
		this.flightID = flightID;
	}

	@Override
	public BaseDTO getDTO(BaseDTO dto) {
		return null;
	}
}
