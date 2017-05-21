package org.rozzie.processor.repositories.neo;

import org.rozzie.processor.models.dao.neo.AirportNeo;
import org.rozzie.processor.models.dao.neo.BaggageNeo;
import org.rozzie.processor.models.dao.neo.PassengerNeo;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Component;

@Component("neoBagRepo")
public interface BaggageRepo extends GraphRepository<BaggageNeo> {

	public BaggageNeo findByBaggageId(String baggageId);
	public PassengerNeo getOwner(String baggageId);
}
