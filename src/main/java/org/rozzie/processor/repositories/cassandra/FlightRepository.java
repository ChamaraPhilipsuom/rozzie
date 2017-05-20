package org.rozzie.processor.repositories.cassandra;

import org.rozzie.processor.models.dao.cassandra.FlightCas;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("cassandraFlightRepo")
public interface FlightRepository extends CassandraRepository<FlightCas> {

	@Query("Select * from flightdao where flightID=?0")
	public FlightCas findByFlightId(UUID flightId);
}
