package org.rozzie.processor.repositories.neo;

import org.rozzie.processor.models.dao.neo.BaggageNeo;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Component;

@Component("neoBagRepo")
public interface BaggageRepo extends GraphRepository<BaggageNeo> {

	public BaggageNeo findByBaggageId(String baggageId);

}
