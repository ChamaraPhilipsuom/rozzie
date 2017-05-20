package org.rozzie.processor.repositories.neo;


import org.rozzie.processor.models.dao.neo.AirportDAO;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Component;

@Component("neoAirRepo")
public interface AirportRepository extends GraphRepository<AirportDAO>{
}
