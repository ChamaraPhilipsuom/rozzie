package org.rozzie.processor.listeners;


import org.rozzie.processor.events.AirlineEvent;
import org.rozzie.processor.events.FlightBookingEvent;
import org.rozzie.processor.models.event.sources.Flight;
import org.rozzie.processor.models.event.sources.Passenger;
import org.rozzie.processor.utils.Constants;

public class FlightEventListener extends AirlineListener{
    @Override
    public void changeReceived(AirlineEvent event) {
        if(event instanceof FlightBookingEvent){
            if(event.getSource() instanceof Flight){
                Flight flight = (Flight) event.getSource();
                System.out.println(Constants.EVENT_MESSAGE + "Message at the airline terminal... \n" + "New passenger" +
                        " added to the " +
                        "flight " +
                        flight.getFlightID());
            } else if (event.getSource() instanceof Passenger) {
                System.out.println(Constants.EVENT_MESSAGE + "Message at the passenger terminal... \n" + "You hae " +
                        "booked the flight " +
                        "successfully.");
            }
        }
    }
}
