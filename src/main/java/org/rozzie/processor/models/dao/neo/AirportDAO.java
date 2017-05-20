package org.rozzie.processor.models.dao.neo;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.UUID;

@NodeEntity
public class AirportDAO {

	@GraphId
	private Long nodeId;
	private String airportId;

	public AirportDAO() {
	}

	public AirportDAO(UUID airportId) {
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
}
