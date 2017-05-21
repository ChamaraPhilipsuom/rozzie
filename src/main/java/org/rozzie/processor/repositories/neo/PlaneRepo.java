package org.rozzie.processor.repositories.neo;

import org.rozzie.processor.models.dao.neo.PlaneNeo;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Component;

@Component("neoPlaneRepo")
public interface PlaneRepo extends GraphRepository<PlaneNeo> {

	public PlaneNeo findByPlaneId(String planeId);
}
