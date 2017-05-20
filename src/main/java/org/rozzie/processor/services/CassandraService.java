package org.rozzie.processor.services;

import org.apache.commons.lang3.StringUtils;
import org.rozzie.processor.listeners.FlightDepartureEventListener;
import org.rozzie.processor.models.Airport;
import org.rozzie.processor.models.Flight;
import org.rozzie.processor.models.dao.cassandra.AirportDAO;
import org.rozzie.processor.models.dao.cassandra.FlightDAO;
import org.rozzie.processor.models.dto.AirportDTO;
import org.rozzie.processor.models.dto.FlightDTO;
import org.rozzie.processor.repositories.cassandra.AirportRepository;
import org.rozzie.processor.repositories.cassandra.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@EnableCassandraRepositories(basePackages = "org.rozzie.processor.repositories.cassandra")
public class CassandraService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    public FlightDTO getFlight(String uuid){
          return getFlightDTO(getFlightFromDAO(flightRepository.findByFlightId(UUID.fromString(uuid))));
    }

    public FlightDTO changeDepatureTime(String flightId, LocalDateTime newDepatureTime){
          UUID uuid = UUID.fromString(flightId);
          Flight flight = getFlightFromDAO(flightRepository.findByFlightId(uuid));
          flight.addListener(new FlightDepartureEventListener());
		  flight.setActualDepatureTime(newDepatureTime);
		  FlightDAO flightDAO = flightRepository.save(new FlightDAO(flight));
		  return getFlightDTO(getFlightFromDAO(flightDAO));
	}
    public AirportDTO getAirPort(String uuid){
    	return  getPortDTO(getPortFromDAO(airportRepository.findByAirportId(UUID.fromString(uuid))));
	}

    /*
    *  Util methods
    */

    private Flight getFlightFromDAO(FlightDAO flightDAO){
        Flight flight = new Flight();
        AirportDAO sourcePortDAO = null;
        AirportDAO destinationPortDAO = null;
		if (flightDAO != null) {
			String[] sourcecPort = StringUtils.isNotBlank(flightDAO.getSource())
					? flightDAO.getSource().split(",")
					: null;
			String[] destinationPort = StringUtils.isNotBlank(flightDAO.getDestination())
					? flightDAO.getDestination().split(",")
					: null;
			if (sourcecPort != null && sourcecPort.length > 0) {
				sourcePortDAO = airportRepository.findByAirportId(UUID.fromString(sourcecPort[0]));
			}
			if(destinationPort != null && destinationPort.length > 0) {
			    destinationPortDAO = airportRepository.findByAirportId(UUID.fromString(destinationPort[0]));
            }
            flight.setFlightID(flightDAO.getFlightID());
			flight.setActualDepatureTime(flightDAO.getActualDepatureTime());
			flight.setActualArrivalTime(flightDAO.getActualArrivalTime());
			flight.setPlannedArrivalTime(flightDAO.getPlannedArrivalTime());
			flight.setPlannedDepatureTime(flightDAO.getPlannedDepatureTime());
			flight.setSource(getPortFromDAO(sourcePortDAO));
			flight.setDestination(getPortFromDAO(destinationPortDAO));
		}
        return flight;
    }

	private FlightDTO getFlightDTO(Flight flight) {
		FlightDTO flightDTO = new FlightDTO();
		if(flight!=null){
		    flightDTO.setFlightID(flight.getFlightID());
		    flightDTO.setActualDepatureTime(flight.getActualDepatureTime());
		    flightDTO.setActualArrivalTime(flight.getActualArrivalTime());
		    flightDTO.setPlannedDepatureTime(flight.getPlannedDepatureTime());
		    flightDTO.setPlannedArrivalTime(flight.getPlannedArrivalTime());
		    flightDTO.setSource(getPortDTO(flight.getSource()));
		    flightDTO.setDestination(getPortDTO(flight.getDestination()));
        }
		return flightDTO;
	}

	private Airport getPortFromDAO(AirportDAO airportDAO) {
		Airport airport = new Airport();
		if (airportDAO != null) {
			airport.setAirportId(airportDAO.getAirportId());
			airport.setCity(airportDAO.getCity());
			airport.setCountry(airportDAO.getCountry());
		}
		return airport;
	}

	private AirportDTO getPortDTO(Airport airport) {
		AirportDTO airportDTO = new AirportDTO();
		if (airport != null) {
			airportDTO.setAirportId(airport.getAirportId());
			airportDTO.setCity(airport.getCity());
			airportDTO.setCountry(airport.getCountry());
		}
		return airportDTO;
	}
}
