package org.rozzie.processor.models;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class Airport extends EventSource {

	private UUID airportId;
	private String city;
	private String country;

	public Airport() {
	}

	public Airport(UUID airportId, String city, String country) {
		this.airportId = airportId;
		this.city = city;
		this.country = country;
	}

	public UUID getAirportId() {
		return airportId;
	}

	public void setAirportId(UUID airportId) {
		this.airportId = airportId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.airportId).append(",").append(this.country).append(",").append(this.city);
		return builder.toString();
	}
}
