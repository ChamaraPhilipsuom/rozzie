package org.rozzie.processor.models.dao.neo;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.rozzie.processor.models.dto.BaggageDTO;
import org.rozzie.processor.models.dto.BaseDTO;
import org.rozzie.processor.models.dto.PassengerDTO;

import java.util.List;
import java.util.UUID;

@NodeEntity
public class BaggageNeo implements BaseNeo {

	@GraphId
	private Long nodeId;
	private String baggageId;

	@Relationship(type = "OWNED_BY", direction = Relationship.OUTGOING)
	private PassengerNeo owner;

	public BaggageNeo() {
	}

	public BaggageNeo(String baggageId) {
		this.baggageId = baggageId;
	}

	public Long getNodeId() {
		return nodeId;
	}

	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}

	public String getBaggageId() {
		return baggageId;
	}

	public void setBaggageId(String baggageId) {
		this.baggageId = baggageId;
	}

	public PassengerNeo getOwner() {
		return owner;
	}

	public void setOwner(PassengerNeo owner) {
		this.owner = owner;
	}

	@Override
	public BaseDTO getDTO(BaseDTO dto) {
		BaggageDTO bag = (BaggageDTO) dto;
		bag.setBaggageId(UUID.fromString(this.baggageId));
		PassengerDTO passenger = bag.getOwner();
		passenger.setPassengerId(UUID.fromString(owner.getPassengerId()));
		bag.setOwner(passenger);
		return bag;
	}
}
