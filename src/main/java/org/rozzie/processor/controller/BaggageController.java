package org.rozzie.processor.controller;

import org.rozzie.processor.models.dto.BaggageDTO;
import org.rozzie.processor.services.CassandraService;
import org.rozzie.processor.services.NeoService;
import org.rozzie.processor.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.RequestUri.Baggage.CONTROLLER)
public class BaggageController {

	@Autowired
	private CassandraService cassandraService;

	@Autowired
	private NeoService neoService;

	@RequestMapping(value = Constants.RequestUri.Baggage.CREATE, method = RequestMethod.POST, produces = "application/json")
	public BaggageDTO createBaggage(@RequestParam String baggageType, @RequestParam float weight) {
		return this.cassandraService.createBaggage(baggageType, weight);
	}
}
