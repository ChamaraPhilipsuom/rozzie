package org.rozzie.processor.listeners;


import org.rozzie.processor.events.AirlineEvent;
import org.rozzie.processor.events.CheckInEvent;
import org.rozzie.processor.utils.Constants;

public class PassengerEventListener extends AirlineListener{
    @Override
    public void changeReceived(AirlineEvent event) {
        if(event instanceof CheckInEvent){
        }
    }
}
