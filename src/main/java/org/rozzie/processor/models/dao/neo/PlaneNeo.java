package org.rozzie.processor.models.dao.neo;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.rozzie.processor.models.dto.BaseDTO;
import org.rozzie.processor.models.dto.PlaneDTO;

import java.util.UUID;

@NodeEntity
public class PlaneNeo implements BaseNeo {

	@GraphId
	private Long nodeId;

	private String planeId;

	public PlaneNeo() {
	}

	public PlaneNeo(String planeId) {
		this.planeId = planeId;
	}

	public String getPlaneId() {
		return planeId;
	}

	public void setPlaneId(String planeId) {
		this.planeId = planeId;
	}

	@Override
	public BaseDTO getDTO(BaseDTO dto) {
		PlaneDTO plane = (PlaneDTO) dto;
		plane.setPlaneId(UUID.fromString(this.planeId));
		return plane;
	}
}
