package org.rozzie.processor.services;

import org.rozzie.processor.models.dao.neo.AirportNeo;
import org.rozzie.processor.models.dao.neo.BaggageNeo;
import org.rozzie.processor.models.dao.neo.FlightNeo;
import org.rozzie.processor.models.dao.neo.PassengerNeo;
import org.rozzie.processor.models.dao.neo.PlaneNeo;
import org.rozzie.processor.models.dto.AirportDTO;
import org.rozzie.processor.models.dto.BaggageDTO;
import org.rozzie.processor.models.dto.FlightDTO;
import org.rozzie.processor.models.dto.PassengerDTO;
import org.rozzie.processor.models.dto.PlaneDTO;
import org.rozzie.processor.repositories.neo.PlaneRepo;
import org.rozzie.processor.repositories.neo.PassengerRepo;
import org.rozzie.processor.repositories.neo.AirportRepo;
import org.rozzie.processor.repositories.neo.BaggageRepo;
import org.rozzie.processor.repositories.neo.FlightRepo;
import org.rozzie.processor.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@EnableNeo4jRepositories(basePackages = "org.rozzie.processor.repositories.neo")
public class NeoService {

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

	public AirportDTO createAirPort(AirportDTO airportDTO) {
		AirportNeo port = airportRepo.findByAirportId(airportDTO.getAirportId().toString());
		if (Util.isNull(port)) {
			port = new AirportNeo(airportDTO.getAirportId());
			port = airportRepo.save(port);
		}
		return (AirportDTO) port.getDTO(airportDTO);
	}

	public BaggageDTO createBaggage(BaggageDTO baggageDTO) {
		BaggageNeo bag = baggageRepo.findByBaggageId(baggageDTO.getBaggageId().toString());
		if (Util.isNull(bag)) {
			bag = new BaggageNeo(baggageDTO.getBaggageId().toString());
		}
		PassengerNeo owner = passengerRepo.findByPassengerId(baggageDTO.getOwner().getPassengerId().toString());
		bag.setOwner(owner);
		bag = baggageRepo.save(bag);
		return (BaggageDTO) bag.getDTO(baggageDTO);
	}

	public FlightNeo createFlight(LocalDateTime plannedArrivalTime, LocalDateTime plannedDepatureTime,
			LocalDateTime actualArrivalTime, LocalDateTime actualDepatureTime, UUID sourceId, UUID destinationId,
			UUID flightId) {

		AirportNeo sourceDao = airportRepo.findByAirportId(sourceId.toString());
		AirportNeo destinationDao = airportRepo.findByAirportId(destinationId.toString());
		if (Util.isNull(sourceDao)) {
			sourceDao = new AirportNeo(sourceId);
			sourceDao = airportRepo.save(sourceDao);
		}
		if (Util.isNull(destinationDao)) {
			destinationDao = new AirportNeo(destinationId);
			destinationDao = airportRepo.save(destinationDao);
		}
		FlightNeo dao = new FlightNeo();
		dao.setFlightId(flightId);
		dao.setSourcePort(sourceDao);
		dao.setDestinationPort(destinationDao);

		return flightRepo.save(dao);

	}

	public PassengerDTO createPassenger(PassengerDTO passengerDTO) {
		PassengerNeo passengerNeo = passengerRepo.findByPassengerId(passengerDTO.getPassengerId().toString());
		if (Util.isNull(passengerNeo)) {
			passengerNeo = new PassengerNeo(passengerDTO.getPassengerId().toString());
		}
		FlightNeo flight = flightRepo.findByFlightId(passengerDTO.getFlightDTO().getFlightID().toString());
		passengerNeo.setFlight(flight);
		passengerNeo = passengerRepo.save(passengerNeo);
		return (PassengerDTO) passengerNeo.getDTO(passengerDTO);
	}

	public PlaneDTO createPlane(PlaneDTO planeDTO) {
		PlaneNeo planeNeo = planeRepo.findByPlaneId(planeDTO.getPlaneId().toString());
		if (Util.isNull(planeNeo)) {
			planeNeo = new PlaneNeo(planeDTO.getPlaneId().toString());
		}
		planeNeo = planeRepo.save(planeNeo);
		return (PlaneDTO) planeNeo.getDTO(planeDTO);
	}

	public BaggageDTO getBaggage(BaggageDTO bag) {
		BaggageNeo bagNeo = baggageRepo.findByBaggageId(bag.getBaggageId().toString());
		return (BaggageDTO) bagNeo.getDTO(bag);
	}

	public FlightDTO getFlight(FlightDTO flight) {
		FlightNeo flightNeo = flightRepo.findByFlightId(flight.getFlightID().toString());
		flight.setSource((AirportDTO) (flightNeo.getSourcePort().getDTO(new AirportDTO())));
		flight.setDestination((AirportDTO) (flightNeo.getSourcePort().getDTO(new AirportDTO())));
		return flight;
	}

	public FlightDTO getPassengerOfFlight(FlightDTO flight) {
		List<PassengerNeo> passengers = flightRepo.getPassengers(flight.getFlightID().toString());
		if (passengers != null && passengers.size() > 0) {
			List<PassengerDTO> passengerDTOS = new ArrayList<PassengerDTO>();
			for (PassengerNeo passenger : passengers) {
				PassengerDTO passengerDTO = new PassengerDTO(UUID.fromString(passenger.getPassengerId()));
				passengerDTOS.add(passengerDTO);
			}
			flight.setPassengers(passengerDTOS);
		}
		return flight;
	}

	public PassengerDTO getPassenger(PassengerDTO passenger) {
		PassengerNeo passengerNeo = passengerRepo.findByPassengerId(passenger.getPassengerId().toString());
		return (PassengerDTO) passengerNeo.getDTO(passenger);
	}

	public PassengerDTO getBaggagesOfPassenger(PassengerDTO passenger) {
		List<BaggageNeo> baggages = passengerRepo.getBaggages(passenger.getPassengerId().toString());
		if (baggages != null && baggages.size() > 0) {
			List<BaggageDTO> baggageDTOS = new ArrayList<BaggageDTO>();
			for (BaggageNeo baggageNeo : baggages) {
				BaggageDTO bag = new BaggageDTO(UUID.fromString(baggageNeo.getBaggageId()));
				baggageDTOS.add(bag);
			}
			passenger.setBaggages(baggageDTOS);
		}
		return passenger;
	}
}
