package org.rozzie.processor.models.dao.cassandra;

import org.rozzie.processor.models.dto.BaseDTO;
import org.rozzie.processor.models.dto.PlaneDTO;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

@Table
public class PlaneCas implements BaseCas {
	@PrimaryKey
	private UUID planeId;
	private String airLine;
	private String planeNumber;

	public PlaneCas() {
	}

	public PlaneCas(UUID planeId, String airLine, String planeNumber) {
		this.planeId = planeId;
		this.airLine = airLine;
		this.planeNumber = planeNumber;
	}

	public UUID getPlaneId() {
		return planeId;
	}

	public void setPlaneId(UUID planeId) {
		this.planeId = planeId;
	}

	public String getAirLine() {
		return airLine;
	}

	public void setAirLine(String airLine) {
		this.airLine = airLine;
	}

	public String getPlaneNumber() {
		return planeNumber;
	}

	public void setPlaneNumber(String planeNumber) {
		this.planeNumber = planeNumber;
	}

	@Override
	public BaseDTO getDTO(BaseDTO dto) {
		PlaneDTO plane = (PlaneDTO) dto;
		plane.setPlaneId(this.planeId);
		plane.setAirLine(this.airLine);
		plane.setPlaneNumber(this.planeNumber);
		return plane;
	}
}
