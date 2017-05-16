package org.rozzie.processor.repositories.neo;

import org.rozzie.processor.models.dao.neo.FlightDAO;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface FlightRepository extends GraphRepository<FlightDAO> {

}
