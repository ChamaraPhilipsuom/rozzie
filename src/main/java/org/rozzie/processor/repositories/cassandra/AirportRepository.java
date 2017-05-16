package org.rozzie.processor.repositories.cassandra;

import org.rozzie.processor.models.dao.cassandra.AirportDAO;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.UUID;

public interface AirportRepository extends CassandraRepository<AirportDAO> {

      @Query("Select * from airportdao where airportId=?0")
      public AirportDAO findByAirportId(UUID airportId);
}
