package org.rozzie.processor.controller;

import org.rozzie.processor.listeners.FlightEventListener;
import org.rozzie.processor.models.dao.neo.FlightNeo;
import org.rozzie.processor.models.dto.AirportDTO;
import org.rozzie.processor.models.dto.FlightDTO;
import org.rozzie.processor.models.dto.PassengerDTO;
import org.rozzie.processor.models.dto.PlaneDTO;
import org.rozzie.processor.models.event.sources.Flight;
import org.rozzie.processor.services.CassandraService;
import org.rozzie.processor.services.NeoService;
import org.rozzie.processor.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(Constants.RequestUri.Flight.CONTROLLER)
public class FlightController {

	@Autowired
	private CassandraService cassandraService;

	@Autowired
	private NeoService neoService;

	@RequestMapping(value = Constants.RequestUri.CREATE, method = RequestMethod.POST, produces = "application/json")
	public FlightDTO createFlight(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime plannedArrivalTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime plannedDepatureTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime actualArrivalTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime actualDepatureTime,
			@RequestParam String sourceId, @RequestParam String destinationId, @RequestParam String planeId) {
		FlightDTO flightDTO = this.cassandraService.createFlight(plannedArrivalTime, plannedDepatureTime,
				actualArrivalTime, actualDepatureTime);
		FlightNeo neoDao = this.neoService.createFlight(plannedArrivalTime, plannedDepatureTime, actualArrivalTime,
				actualDepatureTime, sourceId, destinationId, flightDTO.getFlightID().toString(), planeId);
		return flightDTO;
	}

	@RequestMapping(value = Constants.RequestUri.GET, method = RequestMethod.GET, produces = "application/json")
	public FlightDTO getFlight(@RequestParam String flightId) {
		FlightDTO flight = this.cassandraService.getFlight(flightId);
		flight = this.neoService.getFlight(flight);
		flight = this.neoService.getPassengerOfFlight(flight);
		AirportDTO source = this.cassandraService.getAirPort(flight.getSource().getAirportId().toString());
		AirportDTO destination = this.cassandraService.getAirPort(flight.getDestination().getAirportId().toString());
		PlaneDTO plane = this.cassandraService.getPlane(flight.getPlane().getPlaneId().toString());
		flight.setSource(source);
		flight.setDestination(destination);
		flight.setPlane(plane);
		if (flight.getPassengers() != null && flight.getPassengers().size() > 0) {
			List<PassengerDTO> passengerDTOS = new ArrayList<PassengerDTO>();
			for (PassengerDTO passenger : flight.getPassengers()) {
				passenger = this.cassandraService.getPassenger(passenger.getPassengerId().toString());
				passenger = this.neoService.getBaggagesOfPassenger(passenger);
				passenger.setFlightDTO(null); //no nned of the same flight information
				passengerDTOS.add(passenger);
			}
			flight.setPassengers(passengerDTOS);
		}
		return flight;
	}

	@RequestMapping(value = Constants.RequestUri.Flight.CHANGE_DEPATURE, method = RequestMethod.POST, produces = "application/json")
	public FlightDTO changeDepatureTime(@RequestParam String flightId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newDepatureTime) {
		return this.cassandraService.changeDepatureTime(flightId, newDepatureTime);
	}

	@RequestMapping(value = Constants.RequestUri.Flight.CHANGE_ARRIVAL, method = RequestMethod.POST, produces = "application/json")
	public FlightDTO changeArrivalTime(@RequestParam String flightId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newArrivalTime) {
		return this.cassandraService.changeArrivalTime(flightId, newArrivalTime);
	}

	@RequestMapping(value = Constants.RequestUri.Flight.TAKE_OFF, method = RequestMethod.POST, produces = "application/json")
	public FlightDTO takeOff(@RequestParam String flightId){
    	FlightDTO flightDTO = this.cassandraService.getFlight(flightId);
		Flight flight = new Flight(flightDTO.getFlightID());
		flight.addListener(new FlightEventListener());
		flight.takeOff();
		return flightDTO;
	}
}
