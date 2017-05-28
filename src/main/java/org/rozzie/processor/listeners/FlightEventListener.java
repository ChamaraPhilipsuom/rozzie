package org.rozzie.processor.listeners;


import org.rozzie.processor.events.AirlineEvent;
import org.rozzie.processor.events.FlightBookingEvent;

public class FlightEventListener extends AirlineListener{
    @Override
    public void changeReceived(AirlineEvent event) {
        if(event instanceof FlightBookingEvent){

		}
	}
}
