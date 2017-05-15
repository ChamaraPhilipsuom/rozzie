package org.rozzie.processor;

import org.rozzie.processor.listeners.FlightDepartureEventListener;
import org.rozzie.processor.models.Airport;
import org.rozzie.processor.models.Flight;

import java.time.LocalDateTime;
import java.util.UUID;

import org.rozzie.processor.models.dao.AirportDAO;
import org.rozzie.processor.models.dao.FlightDAO;
import org.rozzie.processor.repositories.cassandra.AirportRepository;
import org.rozzie.processor.repositories.cassandra.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RozzieApp implements CommandLineRunner{

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private AirportRepository airportRepository;

	@Override
	public void run(String... args) throws Exception {
		try {
			flightRepository.deleteAll();
			airportRepository.deleteAll();
			LocalDateTime plannedArrival = LocalDateTime.now().plusHours(1);
			LocalDateTime actualArrival = LocalDateTime.now().plusHours(1);
			LocalDateTime plannedDeparture = LocalDateTime.now().plusHours(3);
			LocalDateTime actualDeparture = LocalDateTime.now().plusHours(3);

			Airport source = new Airport(UUID.randomUUID(), "Colombo", "Sri Lanka");
			Airport destination = new Airport(UUID.randomUUID(), "Bangalore", "India");

			Flight flight = new Flight(UUID.randomUUID(), plannedArrival, plannedDeparture, actualArrival,
					actualDeparture, source, destination);

			flightRepository.save(new FlightDAO(flight));
			airportRepository.save(new AirportDAO(source));
			airportRepository.save(new AirportDAO(destination));

			FlightDepartureEventListener listener = new FlightDepartureEventListener();
			flight.addListener(listener);
			flight.setActualDepatureTime(actualDeparture.plusHours(2));
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}
	public static void main(String[] args) {
		SpringApplication.run(RozzieApp.class, args);
	}
}
