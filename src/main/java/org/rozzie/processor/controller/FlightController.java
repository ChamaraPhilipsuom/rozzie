package org.rozzie.processor.controller;

import org.rozzie.processor.models.dto.FlightDTO;
import org.rozzie.processor.services.CassandraService;
import org.rozzie.processor.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("flight")
public class FlightController {

    @Autowired
    private CassandraService cassandraService;

    @RequestMapping(value = Constants.RequestUri.GET_FLIGHT, method = RequestMethod.GET, produces = "application/json")
    public FlightDTO getFlight(@RequestParam String flightId) {
        return this.cassandraService.getFlight(flightId);
    }
}
