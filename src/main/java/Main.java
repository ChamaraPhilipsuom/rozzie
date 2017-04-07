import org.rozzie.processor.listeners.FlightDepartureEventListener;
import org.rozzie.processor.models.Flight;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by chamarap on 4/6/17.
 */
public class Main {

	public static final void main(String[] args) {
		try {

			LocalDateTime plannedArrival = LocalDateTime.now().plusHours(1);
			LocalDateTime actualArrival = LocalDateTime.now().plusHours(1);
			LocalDateTime plannedDeparture = LocalDateTime.now().plusHours(3);
			LocalDateTime actualDeparture = LocalDateTime.now().plusHours(3);

			Flight flight = new Flight(UUID.randomUUID(), plannedArrival, plannedDeparture, actualArrival,
					actualDeparture);
			FlightDepartureEventListener listener = new FlightDepartureEventListener();
			flight.addListener(listener);
			flight.setActualDepatureTime(actualDeparture.plusHours(2));
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
