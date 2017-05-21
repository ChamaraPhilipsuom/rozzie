package org.rozzie.processor.services;

import org.rozzie.processor.listeners.FlightArrivalEventListener;
import org.rozzie.processor.listeners.FlightDepartureEventListener;
import org.rozzie.processor.models.event.sources.Airport;
import org.rozzie.processor.models.event.sources.Flight;
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
import org.rozzie.processor.repositories.cassandra.AirportRepo;
import org.rozzie.processor.repositories.cassandra.BaggageRepo;
import org.rozzie.processor.repositories.cassandra.FlightRepo;
import org.rozzie.processor.repositories.cassandra.PassengerRepo;
import org.rozzie.processor.repositories.cassandra.PlaneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@EnableCassandraRepositories(basePackages = "org.rozzie.processor.repositories.cassandra")
public class CassandraService {

	@Autowired
	private FlightRepo flightRepo;

	@Autowired
	private AirportRepo airportRepo;

	@Autowired
	private BaggageRepo baggageRepo;

	@Autowired
	private PassengerRepo passengerRepo;

	@Autowired
	private PlaneRepo planeRepo;

	public AirportDTO createPort(String city, String country) {
		AirportCas port = new AirportCas(UUID.randomUUID(), city, country);
		port = airportRepo.save(port);
		return (AirportDTO) port.getDTO(new AirportDTO());
	}

	public BaggageDTO createBaggage(String baggageType, float weight) {
		BaggageCas bag = new BaggageCas(UUID.randomUUID(), baggageType, weight);
		bag = baggageRepo.save(bag);
		return (BaggageDTO) bag.getDTO(new BaggageDTO());
	}

	public FlightDTO createFlight(LocalDateTime plannedArrivalTime, LocalDateTime plannedDepatureTime,
			LocalDateTime actualArrivalTime, LocalDateTime actualDepatureTime) {
		FlightCas flightCas = new FlightCas(UUID.randomUUID(), plannedArrivalTime, plannedDepatureTime,
				actualArrivalTime, actualDepatureTime);
		flightCas = flightRepo.save(flightCas);
		FlightDTO flightDTO = (FlightDTO) flightCas.getDTO(new FlightDTO());
		return flightDTO;

	}

	public PassengerDTO createPassenger(String name, String country, String contact) {
		PassengerCas passenger = new PassengerCas(UUID.randomUUID(), name, country, contact);
		passenger = passengerRepo.save(passenger);
		return (PassengerDTO) passenger.getDTO(new PassengerDTO());
	}

	public PlaneDTO createPlane(String airline, String planeNumber) {
		PlaneCas plane = new PlaneCas(UUID.randomUUID(), airline, planeNumber);
		plane = planeRepo.save(plane);
		return (PlaneDTO) plane.getDTO(new PlaneDTO());
	}

	public AirportDTO getAirPort(String uuid) {
		AirportCas port = airportRepo.findByAirportId(UUID.fromString(uuid));
		return (AirportDTO) port.getDTO(new AirportDTO());
	}

	public BaggageDTO getBaggage(String uuid) {
		BaggageCas bag = baggageRepo.findByBagId(UUID.fromString(uuid));
		return (BaggageDTO) bag.getDTO(new BaggageDTO());
	}

	public FlightDTO getFlight(String uuid) {
		FlightCas flightCas = flightRepo.findByFlightId(UUID.fromString(uuid));
		return (FlightDTO) flightCas.getDTO(new FlightDTO());
	}

	public PassengerDTO getPassenger(String uuid) {
		PassengerCas passenger = passengerRepo.findByPassengerId(UUID.fromString(uuid));
		return (PassengerDTO) passenger.getDTO(new PassengerDTO());
	}

	public PlaneDTO getPlane(String planeId) {
		PlaneCas plane = planeRepo.findByPlaneId(UUID.fromString(planeId));
		return (PlaneDTO) plane.getDTO(new PlaneDTO());
	}

	public FlightDTO changeDepatureTime(String flightId, LocalDateTime newDepatureTime) {
		UUID uuid = UUID.fromString(flightId);
		FlightCas flightCas = flightRepo.findByFlightId(uuid);
		Flight flight = getFlightFromDAO(flightCas);
		flight.addListener(new FlightDepartureEventListener());
		flight.setActualDepatureTime(newDepatureTime);
		flightCas.setActualDepatureTime(newDepatureTime);
		flightCas = flightRepo.save(flightCas);
		return getFlightDTO(getFlightFromDAO(flightCas));
	}

	public FlightDTO changeArrivalTime(String flightId, LocalDateTime newArrivalTime) {
		UUID uuid = UUID.fromString(flightId);
		FlightCas flightCas = flightRepo.findByFlightId(uuid);
		Flight flight = getFlightFromDAO(flightCas);
		flight.addListener(new FlightArrivalEventListener());
		flight.setActualArrivalTime(newArrivalTime);
		flightCas.setActualArrivalTime(newArrivalTime);
		flightCas = flightRepo.save(flightCas);
		return getFlightDTO(getFlightFromDAO(flightCas));
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
