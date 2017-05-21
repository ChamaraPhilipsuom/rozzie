package org.rozzie.processor.models.event.sources;

import java.util.UUID;

public class Baggage extends EventSource {

	private UUID baggageId;
	private String baggageType;
	private float weight;

	public Baggage() {
	}

	public Baggage(UUID baggageId, String baggageType, float weight) {
		this.baggageId = baggageId;
		this.baggageType = baggageType;
		this.weight = weight;
	}

	public UUID getBaggageId() {
		return baggageId;
	}

	public void setBaggageId(UUID baggageId) {
		this.baggageId = baggageId;
	}

	public String getBaggageType() {
		return baggageType;
	}

	public void setBaggageType(String baggageType) {
		this.baggageType = baggageType;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}
}
