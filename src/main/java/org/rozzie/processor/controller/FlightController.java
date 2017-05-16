package org.rozzie.processor.controller;

import org.rozzie.processor.models.dto.FlightDTO;
import org.rozzie.processor.services.CassandraService;
import org.rozzie.processor.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("flight")
public class FlightController {

    @Autowired
    private CassandraService cassandraService;

	@RequestMapping(value = Constants.RequestUri.Flight.GET_FLIGHT, method = RequestMethod.GET, produces = "application/json")
	public FlightDTO getFlight(@RequestParam String flightId) {
		return this.cassandraService.getFlight(flightId);
	}


    @RequestMapping(value = Constants.RequestUri.Flight.CHANGE_DEPATURE, method = RequestMethod.POST, produces = "application/json")
    public FlightDTO changeDepatureTime(@RequestParam String flightId, @RequestParam  @DateTimeFormat(iso = DateTimeFormat
            .ISO.DATE_TIME) LocalDateTime newDepatureTime){
        return this.cassandraService.changeDepatureTime(flightId,newDepatureTime);
    }
}
