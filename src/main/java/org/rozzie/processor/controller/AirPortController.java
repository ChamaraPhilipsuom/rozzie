package org.rozzie.processor.controller;

import org.rozzie.processor.models.Airport;
import org.rozzie.processor.models.dto.AirportDTO;
import org.rozzie.processor.services.CassandraService;
import org.rozzie.processor.services.NeoService;
import org.rozzie.processor.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.RequestUri.AirPort.CONTROLLER)
public class AirPortController {

	@Autowired
	private CassandraService cassandraService;

	@Autowired
	private NeoService neoService;

	@RequestMapping(value = Constants.RequestUri.AirPort.CREATE, method = RequestMethod.POST, produces = "application/json")
	public AirportDTO createPort(@RequestParam String city, @RequestParam String country) {
		AirportDTO airportDTO = this.cassandraService.createPort(city, country);
		return airportDTO;
	}

	@RequestMapping(value = Constants.RequestUri.AirPort.GET, method = RequestMethod.GET, produces = "application/json")
	public AirportDTO getPort(@RequestParam String portId) {
		return this.cassandraService.getAirPort(portId);
	}
}
