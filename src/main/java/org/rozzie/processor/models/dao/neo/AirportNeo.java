package org.rozzie.processor.models.dao.neo;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.rozzie.processor.models.dto.AirportDTO;
import org.rozzie.processor.models.dto.BaseDTO;

import java.util.UUID;

@NodeEntity
public class AirportNeo implements BaseNeo {

	@GraphId
	private Long nodeId;
	private String airportId;

	public AirportNeo() {
	}

	public AirportNeo(UUID airportId) {
		this.airportId = airportId.toString();
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public UUID getAirportId() {
		return UUID.fromString(airportId);
	}

	public void setAirportId(UUID airportId) {
		this.airportId = airportId.toString();
	}

	@Override
	public BaseDTO getDTO(BaseDTO dto) {
		AirportDTO airportDTO = (AirportDTO) dto;
		return airportDTO;
	}
}
