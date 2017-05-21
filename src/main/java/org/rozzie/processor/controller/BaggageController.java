package org.rozzie.processor.controller;

import org.rozzie.processor.models.dto.BaggageDTO;
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
@RequestMapping(Constants.RequestUri.Baggage.CONTROLLER)
public class BaggageController {

	@Autowired
	private CassandraService cassandraService;

	@Autowired
	private NeoService neoService;

	@RequestMapping(value = Constants.RequestUri.CREATE, method = RequestMethod.POST, produces = "application/json")
	public BaggageDTO createBaggage(@RequestParam String baggageType, @RequestParam float weight,
			@RequestParam String passengerId) {
		BaggageDTO bag = this.cassandraService.createBaggage(baggageType, weight);
		bag.setOwner(new PassengerDTO(UUID.fromString(passengerId)));
		bag = this.neoService.createBaggage(bag);
		return bag;
	}

	@RequestMapping(value = Constants.RequestUri.GET, method = RequestMethod.GET, produces = "application/json")
	public BaggageDTO getBaggage(@RequestParam String bagId) {
		BaggageDTO bag = this.cassandraService.getBaggage(bagId);
		bag = this.neoService.getBaggage(bag);
		bag.setOwner(this.cassandraService.getPassenger(bag.getOwner().getPassengerId().toString()));
		return bag;
	}
}
