package org.rozzie.processor.models.dao.neo;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.rozzie.processor.models.dto.BaseDTO;

@NodeEntity
public class PlaneNeo implements BaseNeo {

	@GraphId
	private Long nodeId;

	private String planeId;
	private String planeNumber;

	public String getPlaneId() {
		return planeId;
	}

	public void setPlaneId(String planeId) {
		this.planeId = planeId;
	}

	public String getPlaneNumber() {
		return planeNumber;
	}

	public void setPlaneNumber(String planeNumber) {
		this.planeNumber = planeNumber;
	}

	@Override
	public BaseDTO getDTO(BaseDTO dto) {
		return null;
	}
}
