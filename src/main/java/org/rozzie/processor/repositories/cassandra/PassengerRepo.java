package org.rozzie.processor.repositories.cassandra;

import org.rozzie.processor.models.dao.cassandra.PassengerCas;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("cassandraPassengerRepo")
public interface PassengerRepo extends CassandraRepository<PassengerCas> {

	@Query("Select * from passengercas where passengerId=?0")
	public PassengerCas findByPassengerId(UUID passengerId);
}
