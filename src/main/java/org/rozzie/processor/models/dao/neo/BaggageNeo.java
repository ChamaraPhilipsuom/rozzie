package org.rozzie.processor.models.dao.neo;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.rozzie.processor.models.dto.BaseDTO;

@NodeEntity
public class BaggageNeo implements BaseNeo {

	@GraphId
	private Long nodeId;
	private String baggageId;

	@Relationship(type = "OWNED_BY", direction = Relationship.OUTGOING)
	private PassengerNeo owner;

	public BaggageNeo() {
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

	@Override
	public BaseDTO getDTO(BaseDTO dto) {
		return null;
	}
}
