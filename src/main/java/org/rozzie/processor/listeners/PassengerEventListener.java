package org.rozzie.processor.listeners;


import org.rozzie.processor.events.AirlineEvent;
import org.rozzie.processor.events.CheckInEvent;
import org.rozzie.processor.models.event.sources.Passenger;
import org.rozzie.processor.utils.Constants;

public class PassengerEventListener extends AirlineListener{
    @Override
    public void changeReceived(AirlineEvent event) {
        if(event instanceof CheckInEvent){
            if(event.getSource() instanceof Passenger) {
                System.out.println(Constants.EVENT_MESSAGE + "Message at the passenger terminal... +\nYou have " +
                        "successfully checked into the flight. \n");
            }
        }
    }
}
