package org.rozzie.processor.models.dao.cassandra;

import org.rozzie.processor.models.dto.BaseDTO;
import org.rozzie.processor.models.dto.PassengerDTO;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

@Table
public class PassengerCas implements BaseCas {

	@PrimaryKey
	private UUID passengerId;
	private String name;
	private String country;
	private String contact;

	public PassengerCas() {
	}

	public PassengerCas(UUID passengerId, String name, String country, String contact) {
		this.passengerId = passengerId;
		this.name = name;
		this.country = country;
		this.contact = contact;
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

	@Override
	public BaseDTO getDTO(BaseDTO dto) {
		PassengerDTO passenger = (PassengerDTO) dto;
		passenger.setPassengerId(this.passengerId);
		passenger.setName(this.name);
		passenger.setCountry(this.country);
		passenger.setContact(this.contact);
		return passenger;
	}
}
