package org.rozzie.processor.services;

import org.rozzie.processor.models.Airport;
import org.rozzie.processor.models.Flight;
import org.rozzie.processor.models.dao.neo.AirportDAO;
import org.rozzie.processor.models.dao.neo.FlightDAO;
import org.rozzie.processor.models.dto.FlightDTO;
import org.rozzie.processor.repositories.neo.AirportRepository;
import org.rozzie.processor.repositories.neo.FlightRepository;
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

    public FlightDTO createFlight(LocalDateTime plannedArrivalTime, LocalDateTime plannedDepatureTime, LocalDateTime actualArrivalTime,
                                  LocalDateTime actualDepatureTime, UUID sourceId, UUID destinationId){
        Airport source = new Airport();
        Airport destination = new Airport();
        source.setAirportId(sourceId);
        destination.setAirportId(destinationId);
//        airportRepository.save(source);
//        airportRepository.save(destination);
        Flight flight = new Flight(UUID.randomUUID(),plannedArrivalTime, plannedDepatureTime, actualArrivalTime,
                actualDepatureTime,source,destination);

        AirportDAO sourceDao = new AirportDAO();
        sourceDao.setAirportId(UUID.randomUUID());

        AirportDAO destinationDAO = new AirportDAO();
        destinationDAO.setAirportId(UUID.randomUUID());
        FlightDAO dao = new FlightDAO();
        dao.setFlightId(UUID.randomUUID());
        dao.setDestinationPort(destinationDAO);

        airportRepository.save(sourceDao);
        airportRepository.save(destinationDAO);
        flightRepository.save(dao);

        return null;

    }
}
