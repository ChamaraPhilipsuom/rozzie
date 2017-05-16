package org.rozzie.processor.services;

import org.rozzie.processor.repositories.cassandra.AirportRepository;
import org.rozzie.processor.repositories.neo.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by chamarap on 5/16/17.
 */
public class NeoService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;
}
