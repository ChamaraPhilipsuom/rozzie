package org.rozzie.processor.models.dao.neo;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.rozzie.processor.models.dto.AirportDTO;
import org.rozzie.processor.models.dto.BaseDTO;
import org.rozzie.processor.models.dto.FlightDTO;
import org.rozzie.processor.models.dto.PlaneDTO;

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

	@Relationship(type = "AIR_BUS", direction = Relationship.OUTGOING)
	private PlaneNeo plane;

	public FlightNeo() {
	}
	public FlightNeo(String flightId) {
		this.flightId = flightId;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
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

	public PlaneNeo getPlane() {
		return plane;
	}

	public void setPlane(PlaneNeo plane) {
		this.plane = plane;
	}

	@Override
	public BaseDTO getDTO(BaseDTO dto) {
		FlightDTO flight = (FlightDTO) dto;
		flight.setFlightID(UUID.fromString(this.flightId));
		flight.setSource((AirportDTO) sourcePort.getDTO(flight.getSource()));
		flight.setDestination((AirportDTO) destinationPort.getDTO(flight.getDestination()));
		flight.setPlane((PlaneDTO) plane.getDTO(flight.getPlane()));
		return flight;
	}
}
