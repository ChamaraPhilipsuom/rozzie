package org.rozzie.processor.repositories.cassandra;


import com.datastax.driver.core.utils.UUIDs;
import org.rozzie.processor.models.dao.FlightDAO;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.UUID;

public interface FlightRepository extends CassandraRepository<FlightDAO> {

    @Query("Select * from flightdao where flightID=?0")
    public FlightDAO findByFlightId(UUID flightId);
}
