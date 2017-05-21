package org.rozzie.processor.repositories.cassandra;

import org.rozzie.processor.models.dao.cassandra.BaggageCas;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("cassandraBagRepo")
public interface BaggageRepo extends CassandraRepository<BaggageCas> {
	@Query("Select * from baggagecas where baggageId=?0")
	public BaggageCas findByBagId(UUID baggageId);
}
