package org.rozzie.processor.repositories.cassandra;

import org.rozzie.processor.models.dao.cassandra.PlaneCas;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Component;

@Component("cassandraPlaneRepo")
public interface PlaneRepo extends CassandraRepository<PlaneCas> {

	@Query("Select * from plane where planeId=?0")
	public PlaneCas findByPlaneId(String planeId);
}
