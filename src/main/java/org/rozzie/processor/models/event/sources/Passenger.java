package org.rozzie.processor.models.event.sources;

import org.rozzie.processor.events.FlightBookingEvent;
import org.rozzie.processor.listeners.FlightEventListener;

import java.util.List;
import java.util.UUID;

public class Passenger extends EventSource {

	private UUID passengerId;
	private String name;
	private String country;
	private String contact;
	private Flight flight;
	private List<Baggage> baggages;

	public Passenger(){}

	public Passenger(UUID passengerId, String name){
		this.passengerId = passengerId;
		this.name = name;
		this.addListener(new FlightEventListener());
		fireChangeEvent(new FlightBookingEvent(this));
	}

	public UUID getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(UUID passengerId) {
		this.passengerId = passengerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<Baggage> getBaggages() {
		return baggages;
	}

	public void setBaggages(List<Baggage> baggages) {
		this.baggages = baggages;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
}
