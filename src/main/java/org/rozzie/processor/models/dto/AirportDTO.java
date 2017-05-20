package org.rozzie.processor.models.dto;

import org.rozzie.processor.models.dao.cassandra.BaseCas;
import org.rozzie.processor.models.dao.neo.BaseNeo;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

@XmlRootElement
@XmlType
public class AirportDTO implements BaseDTO {

	private UUID airportId;
	private String city;
	private String country;

	public AirportDTO() {
	}

	public AirportDTO(UUID airportId, String city, String country) {
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
	public BaseDTO completeObject(Object... objects) {
		return null;
	}
}
