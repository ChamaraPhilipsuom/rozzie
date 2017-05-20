package org.rozzie.processor.services;

import org.rozzie.processor.models.dao.neo.AirportNeo;
import org.rozzie.processor.models.dao.neo.FlightNeo;
import org.rozzie.processor.repositories.neo.AirportRepository;
import org.rozzie.processor.repositories.neo.FlightRepository;
import org.rozzie.processor.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@EnableNeo4jRepositories(basePackages = "org.rozzie.processor.repositories.neo")
public class NeoService {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private AirportRepository airportRepository;

	public FlightNeo createFlight(LocalDateTime plannedArrivalTime, LocalDateTime plannedDepatureTime,
			LocalDateTime actualArrivalTime, LocalDateTime actualDepatureTime, UUID sourceId, UUID destinationId,
			UUID flightId) {

		AirportNeo sourceDao = airportRepository.findByAirportId(sourceId.toString());
		AirportNeo destinationDao = airportRepository.findByAirportId(destinationId.toString());
		if (Util.isNull(sourceDao)) {
			sourceDao = new AirportNeo(sourceId);
			sourceDao = airportRepository.save(sourceDao);
		}
		if (Util.isNull(destinationDao)) {
			destinationDao = new AirportNeo(destinationId);
			destinationDao = airportRepository.save(destinationDao);
		}
		FlightNeo dao = new FlightNeo();
		dao.setFlightId(flightId);
		dao.setSourcePort(sourceDao);
		dao.setDestinationPort(destinationDao);

		return flightRepository.save(dao);

	}
}
