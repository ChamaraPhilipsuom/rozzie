package org.rozzie.processor.models.dao.cassandra;

import com.datastax.driver.core.utils.UUIDs;
import org.rozzie.processor.models.Airport;
import org.rozzie.processor.models.dto.AirportDTO;
import org.rozzie.processor.models.dto.BaseDTO;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

@Table
public class AirportCas implements BaseCas {

	@PrimaryKey
	private UUID airportId;
	private String city;
	private String country;

	public AirportCas() {
	}
	public AirportCas(UUID airportId, String city, String country) {
		this.airportId = airportId;
		this.city = city;
		this.country = country;
	}

	public AirportCas(Airport airport) {
		this.airportId = airport.getAirportId();
		this.city = airport.getCity();
		this.country = airport.getCountry();
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
	public BaseDTO getDTO(BaseDTO dto) {
		AirportDTO airportDTO = (AirportDTO) dto;
		airportDTO.setAirportId(this.airportId);
		airportDTO.setCity(this.city);
		airportDTO.setCountry(this.country);
		return airportDTO;
	}
}
