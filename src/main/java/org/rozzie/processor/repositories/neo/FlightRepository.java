package org.rozzie.processor.repositories.neo;

import org.rozzie.processor.models.dao.neo.FlightNeo;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Component;

@Component("neoFlightRepo")
public interface FlightRepository extends GraphRepository<FlightNeo> {

	public FlightNeo findByFlightId(String flightId);

}
