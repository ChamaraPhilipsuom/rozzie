package org.rozzie.processor.repositories.cassandra;

import org.rozzie.processor.models.dao.cassandra.AirportCas;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("cassandraAirRepo")
public interface AirportRepository extends CassandraRepository<AirportCas> {

	@Query("Select * from airportdao where airportId=?0")
	public AirportCas findByAirportId(UUID airportId);
}
