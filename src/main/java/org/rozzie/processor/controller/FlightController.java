package org.rozzie.processor.controller;

import org.rozzie.processor.models.dao.neo.FlightNeo;
import org.rozzie.processor.models.dto.FlightDTO;
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
import java.util.UUID;

@RestController
@RequestMapping(Constants.RequestUri.Flight.CONTROLLER)
public class FlightController {

	@Autowired
	private CassandraService cassandraService;

	@Autowired
	private NeoService neoService;

	@RequestMapping(value = Constants.RequestUri.Flight.CREATE, method = RequestMethod.POST, produces = "application/json")
	public FlightDTO createFlight(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime plannedArrivalTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime plannedDepatureTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime actualArrivalTime,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime actualDepatureTime,
			@RequestParam String sourceId, @RequestParam String destinationId) {
		FlightDTO flightDTO = this.cassandraService.createFlight(plannedArrivalTime, plannedDepatureTime,
				actualArrivalTime, actualDepatureTime, UUID.fromString(sourceId), UUID.fromString(destinationId));
		FlightNeo neoDao = this.neoService.createFlight(plannedArrivalTime, plannedDepatureTime, actualArrivalTime,
				actualDepatureTime, UUID.fromString(sourceId), UUID.fromString(destinationId), flightDTO.getFlightID());
		return flightDTO;
	}

	@RequestMapping(value = Constants.RequestUri.Flight.GET, method = RequestMethod.GET, produces = "application/json")
	public FlightDTO getFlight(@RequestParam String flightId) {
		FlightDTO flightDTO = new FlightDTO();
		return this.cassandraService.getFlight(flightId, flightDTO);
	}

	@RequestMapping(value = Constants.RequestUri.Flight.CHANGE_DEPATURE, method = RequestMethod.POST, produces = "application/json")
	public FlightDTO changeDepatureTime(@RequestParam String flightId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newDepatureTime) {
		return this.cassandraService.changeDepatureTime(flightId, newDepatureTime);
	}
}
