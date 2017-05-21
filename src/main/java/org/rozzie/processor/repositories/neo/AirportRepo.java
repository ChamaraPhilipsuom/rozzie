package org.rozzie.processor.repositories.neo;

import org.rozzie.processor.models.dao.neo.AirportNeo;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Component;

@Component("neoAirRepo")
public interface AirportRepo extends GraphRepository<AirportNeo> {

	public AirportNeo findByAirportId(String airportId);
}
