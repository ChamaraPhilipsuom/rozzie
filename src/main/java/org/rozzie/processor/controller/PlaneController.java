package org.rozzie.processor.controller;

import org.rozzie.processor.models.dto.PlaneDTO;
import org.rozzie.processor.services.CassandraService;
import org.rozzie.processor.services.NeoService;
import org.rozzie.processor.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.RequestUri.Plane.CONTROLLER)
public class PlaneController {

	@Autowired
	private CassandraService cassandraService;

	@Autowired
	private NeoService neoService;

	@RequestMapping(value = Constants.RequestUri.CREATE, method = RequestMethod.POST, produces = "application/json")
	public PlaneDTO createPlane(@RequestParam String airline, @RequestParam String planeNumber) {
		PlaneDTO plane = cassandraService.createPlane(airline, planeNumber);
		plane = neoService.createPlane(plane);
		return plane;
	}

	@RequestMapping(value = Constants.RequestUri.GET, method = RequestMethod.GET, produces = "application/json")
	public PlaneDTO getPlane(@RequestParam String planeId) {
		PlaneDTO plane = cassandraService.getPlane(planeId);
		return plane;
	}
}
