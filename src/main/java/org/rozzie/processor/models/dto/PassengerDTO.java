package org.rozzie.processor.models.dto;

import org.rozzie.processor.models.Baggage;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.UUID;

@XmlRootElement
@XmlType
public class PassengerDTO implements BaseDTO {
	private UUID passengerId;
	private String name;
	private String country;
	private String contact;
	private FlightDTO flightDTO;
	private List<BaggageDTO> baggages;

	public PassengerDTO() {
	}

	public PassengerDTO(UUID passengerId) {
		this.passengerId = passengerId;
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

	public List<BaggageDTO> getBaggages() {
		return baggages;
	}

	public void setBaggages(List<BaggageDTO> baggages) {
		this.baggages = baggages;
	}

	public FlightDTO getFlightDTO() {
		return flightDTO;
	}

	public void setFlightDTO(FlightDTO flightDTO) {
		this.flightDTO = flightDTO;
	}

	@Override
	public BaseDTO completeObject(Object... objects) {
		return null;
	}
}
