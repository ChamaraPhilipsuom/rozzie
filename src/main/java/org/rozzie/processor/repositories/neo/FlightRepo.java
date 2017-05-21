package org.rozzie.processor.repositories.neo;

import org.rozzie.processor.models.dao.neo.FlightNeo;
import org.rozzie.processor.models.dao.neo.PassengerNeo;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("neoFlightRepo")
public interface FlightRepo extends GraphRepository<FlightNeo> {

	public FlightNeo findByFlightId(String flightId);

	@Query("Match (flight:FlightNeo)<-[:FLY_IN]-(passengers) WHERE flight" + ".flightId = {flightId} RETURN passengers")
	public List<PassengerNeo> getPassengers(@Param("flightId") String flightId);

}
