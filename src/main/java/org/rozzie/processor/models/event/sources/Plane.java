package org.rozzie.processor.models.event.sources;

import java.util.UUID;

public class Plane extends EventSource {
	private UUID planeId;
	private String airLine;
	private String planeNumber;

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
}
