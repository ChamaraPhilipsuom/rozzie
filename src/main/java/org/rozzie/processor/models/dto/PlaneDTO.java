package org.rozzie.processor.models.dto;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.UUID;

@XmlRootElement
@XmlType
public class PlaneDTO implements BaseDTO {
	private UUID planeId;
	private String airLine;
	private String planeNumber;

	public PlaneDTO() {
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
	public BaseDTO completeObject(Object... objects) {
		return null;
	}
}
