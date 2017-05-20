package org.rozzie.processor.repositories.neo;

import org.rozzie.processor.models.dao.neo.AirportNeo;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Component;

@Component("neoAirRepo")
public interface AirportRepository extends GraphRepository<AirportNeo> {

	public AirportNeo findByAirportId(String airportId);
}
