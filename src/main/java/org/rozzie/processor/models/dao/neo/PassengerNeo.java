package org.rozzie.processor.models.dao.neo;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.rozzie.processor.models.dto.BaseDTO;

import java.util.List;

@NodeEntity
public class PassengerNeo implements BaseNeo {

	@GraphId
	private Long nodeId;
	private String passengerId;

	@Relationship(type = "FLY_IN", direction = Relationship.OUTGOING)
	private FlightNeo flight;

	@Relationship(type = "OWNED_BY", direction = Relationship.INCOMING)
	private List<BaggageNeo> baggages;

	public PassengerNeo() {
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

	public List<BaggageNeo> getBaggages() {
		return baggages;
	}

	public void setBaggages(List<BaggageNeo> baggages) {
		this.baggages = baggages;
	}

	public FlightNeo getFlight() {
		return flight;
	}

	public void setFlight(FlightNeo flight) {
		this.flight = flight;
	}

	@Override
	public BaseDTO getDTO(BaseDTO dto) {
		return null;
	}
}
