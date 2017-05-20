package org.rozzie.processor.controller;

import org.rozzie.processor.models.dto.PassengerDTO;
import org.rozzie.processor.services.CassandraService;
import org.rozzie.processor.services.NeoService;
import org.rozzie.processor.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.RequestUri.Passenger.CONTROLLER)
public class PassengerController {

	@Autowired
	private CassandraService cassandraService;

	@Autowired
	private NeoService neoService;

	@RequestMapping(value = Constants.RequestUri.Passenger.CREATE, method = RequestMethod.POST, produces = "application/json")
	public PassengerDTO createPassenger(@RequestParam String name, @RequestParam String country,
			@RequestParam String contact) {
		PassengerDTO passengerDTO = cassandraService.createPassenger(name, country, contact);
		return passengerDTO;
	}
}
