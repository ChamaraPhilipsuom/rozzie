package org.rozzie.processor.controller;

import org.rozzie.processor.models.dto.BaggageDTO;
import org.rozzie.processor.models.dto.FlightDTO;
import org.rozzie.processor.models.dto.PassengerDTO;
import org.rozzie.processor.services.CassandraService;
import org.rozzie.processor.services.NeoService;
import org.rozzie.processor.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(Constants.RequestUri.Passenger.CONTROLLER)
public class PassengerController {

	@Autowired
	private CassandraService cassandraService;

	@Autowired
	private NeoService neoService;

	@RequestMapping(value = Constants.RequestUri.CREATE, method = RequestMethod.POST, produces = "application/json")
	public PassengerDTO createPassenger(@RequestParam String name, @RequestParam String country,
			@RequestParam String contact, @RequestParam String flightId) {
		PassengerDTO passengerDTO = cassandraService.createPassenger(name, country, contact);
		FlightDTO flightDTO = new FlightDTO(UUID.fromString(flightId));
		passengerDTO.setFlightDTO(flightDTO);
		passengerDTO = neoService.createPassenger(passengerDTO);
		return passengerDTO;
	}

	@RequestMapping(value = Constants.RequestUri.GET, method = RequestMethod.GET, produces = "application/json")
	public PassengerDTO getPassenger(@RequestParam String passengerId) {
		PassengerDTO passenger = this.cassandraService.getPassenger(passengerId);
		passenger = this.neoService.getPassenger(passenger);
		passenger = this.neoService.getBaggagesOfPassenger(passenger);
		if (passenger.getBaggages() != null && passenger.getBaggages().size() > 0) {
			for (BaggageDTO baggageDTO : passenger.getBaggages()) {
				baggageDTO = this.cassandraService.getBaggage(baggageDTO.getBaggageId().toString());
			}
		}
		return passenger;
	}

}
