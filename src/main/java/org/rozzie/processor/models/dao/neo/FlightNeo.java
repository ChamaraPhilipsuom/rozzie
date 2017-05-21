package org.rozzie.processor.models.dao.neo;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.rozzie.processor.models.dto.AirportDTO;
import org.rozzie.processor.models.dto.BaseDTO;
import org.rozzie.processor.models.dto.FlightDTO;

import java.util.UUID;

@NodeEntity
public class FlightNeo implements BaseNeo {

	@GraphId
	private Long nodeId;
	private String flightId;

	@Relationship(type = "SOURCE_PORT", direction = Relationship.OUTGOING)
	private AirportNeo sourcePort;

	@Relationship(type = "DEST_PORT", direction = Relationship.OUTGOING)
	private AirportNeo destinationPort;

	public FlightNeo() {
	}
	public FlightNeo(UUID flightId) {
		this.flightId = flightId.toString();
	}

	public Long getNodeId() {
		return nodeId;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(UUID flightId) {
		this.flightId = flightId.toString();
	}

	public AirportNeo getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(AirportNeo sourcePort) {
		this.sourcePort = sourcePort;
	}

	public AirportNeo getDestinationPort() {
		return destinationPort;
	}

	public void setDestinationPort(AirportNeo destinationPort) {
		this.destinationPort = destinationPort;
	}

	@Override
	public BaseDTO getDTO(BaseDTO dto) {
		FlightDTO flight = (FlightDTO) dto;
		flight.setFlightID(UUID.fromString(this.flightId));
		flight.setSource((AirportDTO) sourcePort.getDTO(new AirportDTO()));
		flight.setDestination((AirportDTO) destinationPort.getDTO(new AirportDTO()));
		return flight;
	}
}
