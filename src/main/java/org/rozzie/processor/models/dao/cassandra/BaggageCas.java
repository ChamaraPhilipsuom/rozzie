package org.rozzie.processor.models.dao.cassandra;

import org.rozzie.processor.models.dto.BaggageDTO;
import org.rozzie.processor.models.dto.BaseDTO;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

@Table
public class BaggageCas implements BaseCas {
	@PrimaryKey
	private UUID baggageId;
	private String baggageType;
	private float weight;

	public BaggageCas() {
	}

	public BaggageCas(UUID baggageId, String baggageType, float weight) {
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

	@Override
	public BaseDTO getDTO(BaseDTO dto) {
		BaggageDTO bag = (BaggageDTO) dto;
		bag.setBaggageId(this.baggageId);
		bag.setBaggageType(this.baggageType);
		bag.setWeight(this.weight);
		return bag;
	}
}
