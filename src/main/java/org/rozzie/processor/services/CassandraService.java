package org.rozzie.processor.services;

import org.apache.commons.lang3.StringUtils;
import org.rozzie.processor.listeners.FlightDepartureEventListener;
import org.rozzie.processor.models.Airport;
import org.rozzie.processor.models.Flight;
import org.rozzie.processor.models.dao.cassandra.AirportCas;
import org.rozzie.processor.models.dao.cassandra.BaggageCas;
import org.rozzie.processor.models.dao.cassandra.FlightCas;
import org.rozzie.processor.models.dao.cassandra.PassengerCas;
import org.rozzie.processor.models.dao.cassandra.PlaneCas;
import org.rozzie.processor.models.dto.AirportDTO;
import org.rozzie.processor.models.dto.BaggageDTO;
import org.rozzie.processor.models.dto.FlightDTO;
import org.rozzie.processor.models.dto.PassengerDTO;
import org.rozzie.processor.models.dto.PlaneDTO;
import org.rozzie.processor.repositories.cassandra.AirportRepository;
import org.rozzie.processor.repositories.cassandra.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@EnableCassandraRepositories(basePackages = "org.rozzie.processor.repositories.cassandra")
public class CassandraService {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private AirportRepository airportRepository;

	public AirportDTO createPort(String city, String country) {
		AirportCas port = new AirportCas(UUID.randomUUID(), city, country);
		port = airportRepository.save(port);
		return (AirportDTO) port.getDTO(new AirportDTO());
	}

	public FlightDTO createFlight(LocalDateTime plannedArrivalTime, LocalDateTime plannedDepatureTime,
			LocalDateTime actualArrivalTime, LocalDateTime actualDepatureTime, UUID sourceId, UUID destinationId) {
		AirportCas sourceDao = airportRepository.findByAirportId(sourceId);
		AirportCas destinatinDao = airportRepository.findByAirportId(destinationId);
		FlightCas flightCas = new FlightCas(UUID.randomUUID(), plannedArrivalTime, plannedDepatureTime,
				actualArrivalTime, actualDepatureTime);
		flightCas = flightRepository.save(flightCas);
		AirportDTO sourceDTO = new AirportDTO(sourceDao.getAirportId(), sourceDao.getCity(), sourceDao.getCountry());
		AirportDTO destDTO = new AirportDTO(destinatinDao.getAirportId(), destinatinDao.getCity(),
				destinatinDao.getCountry());
		FlightDTO flightDTO = new FlightDTO(flightCas.getFlightID(), flightCas.getPlannedArrivalTime(),
				flightCas.getPlannedDepatureTime(), flightCas.getActualArrivalTime(), flightCas.getActualDepatureTime(),
				sourceDTO, destDTO);
		return flightDTO;

	}

	public PassengerDTO createPassenger(String name, String country, String contact) {
		PassengerCas passenger = new PassengerCas(UUID.randomUUID(), name, country, contact);
		return (PassengerDTO) passenger.getDTO(new PassengerDTO());
	}

	public BaggageDTO createBaggage(String baggageType, float weight) {
		BaggageCas bag = new BaggageCas(UUID.randomUUID(), baggageType, weight);
		return (BaggageDTO) bag.getDTO(new BaggageDTO());
	}

	public PlaneDTO createPlane(String airline, String planeNumber) {
		PlaneCas plane = new PlaneCas(UUID.randomUUID(), airline, planeNumber);
		return (PlaneDTO) plane.getDTO(new PlaneDTO());
	}

	public FlightDTO getFlight(String uuid, FlightDTO flightDTO) {
		FlightCas flightCas = flightRepository.findByFlightId(UUID.fromString(uuid));
		return (FlightDTO) flightDTO.readCassandra(flightCas);
	}

	public FlightDTO changeDepatureTime(String flightId, LocalDateTime newDepatureTime) {
		UUID uuid = UUID.fromString(flightId);
		Flight flight = getFlightFromDAO(flightRepository.findByFlightId(uuid));
		flight.addListener(new FlightDepartureEventListener());
		flight.setActualDepatureTime(newDepatureTime);
		FlightCas flightCas = flightRepository.save(new FlightCas(flight));
		return getFlightDTO(getFlightFromDAO(flightCas));
	}
	public AirportDTO getAirPort(String uuid) {
		return getPortDTO(getPortFromDAO(airportRepository.findByAirportId(UUID.fromString(uuid))));
	}

	/*
	 * Util methods
	 */

	private Flight getFlightFromDAO(FlightCas flightCas) {
		Flight flight = new Flight();
		AirportCas sourcePortDAO = null;
		AirportCas destinationPortDAO = null;
		if (flightCas != null) {
			flight.setFlightID(flightCas.getFlightID());
			flight.setActualDepatureTime(flightCas.getActualDepatureTime());
			flight.setActualArrivalTime(flightCas.getActualArrivalTime());
			flight.setPlannedArrivalTime(flightCas.getPlannedArrivalTime());
			flight.setPlannedDepatureTime(flightCas.getPlannedDepatureTime());
			flight.setSource(getPortFromDAO(sourcePortDAO));
			flight.setDestination(getPortFromDAO(destinationPortDAO));
		}
		return flight;
	}

	private FlightDTO getFlightDTO(Flight flight) {
		FlightDTO flightDTO = new FlightDTO();
		if (flight != null) {
			flightDTO.setFlightID(flight.getFlightID());
			flightDTO.setActualDepatureTime(flight.getActualDepatureTime());
			flightDTO.setActualArrivalTime(flight.getActualArrivalTime());
			flightDTO.setPlannedDepatureTime(flight.getPlannedDepatureTime());
			flightDTO.setPlannedArrivalTime(flight.getPlannedArrivalTime());
			flightDTO.setSource(getPortDTO(flight.getSource()));
			flightDTO.setDestination(getPortDTO(flight.getDestination()));
		}
		return flightDTO;
	}

	private Airport getPortFromDAO(AirportCas airportCas) {
		Airport airport = new Airport();
		if (airportCas != null) {
			airport.setAirportId(airportCas.getAirportId());
			airport.setCity(airportCas.getCity());
			airport.setCountry(airportCas.getCountry());
		}
		return airport;
	}

	private AirportDTO getPortDTO(Airport airport) {
		AirportDTO airportDTO = new AirportDTO();
		if (airport != null) {
			airportDTO.setAirportId(airport.getAirportId());
			airportDTO.setCity(airport.getCity());
			airportDTO.setCountry(airport.getCountry());
		}
		return airportDTO;
	}
}
