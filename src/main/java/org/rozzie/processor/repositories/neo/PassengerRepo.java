package org.rozzie.processor.repositories.neo;

import org.rozzie.processor.models.dao.neo.BaggageNeo;
import org.rozzie.processor.models.dao.neo.PassengerNeo;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("neoPassengerRepo")
public interface PassengerRepo extends GraphRepository<PassengerNeo> {

	public PassengerNeo findByPassengerId(String passengerId);

	@Query("Match (passenger:PassengerNeo)<-[:OWNED_BY]-(baggages) WHERE passenger.passengerId = {passengerId} RETURN"
			+ " baggages")
	public List<BaggageNeo> getBaggages(@Param("passengerId") String passengerId);
}
