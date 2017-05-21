package org.rozzie.processor.models.dao.neo;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.rozzie.processor.models.dto.BaseDTO;
import org.rozzie.processor.models.dto.FlightDTO;
import org.rozzie.processor.models.dto.PassengerDTO;

import java.util.List;
import java.util.UUID;

@NodeEntity
public class PassengerNeo implements BaseNeo {

	@GraphId
	private Long nodeId;
	private String passengerId;

	@Relationship(type = "FLY_IN", direction = Relationship.OUTGOING)
	private FlightNeo flight;

	public PassengerNeo() {
	}

	public PassengerNeo(String passengerId) {
		this.passengerId = passengerId;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}

	public FlightNeo getFlight() {
		return flight;
	}

	public void setFlight(FlightNeo flight) {
		this.flight = flight;
	}

	@Override
	public BaseDTO getDTO(BaseDTO dto) {
		PassengerDTO passengerDTO = (PassengerDTO) dto;
		passengerDTO.setPassengerId(UUID.fromString(passengerId));
		passengerDTO.setFlightDTO((FlightDTO) (flight.getDTO(passengerDTO.getFlightDTO())));
		return passengerDTO;
	}
}
