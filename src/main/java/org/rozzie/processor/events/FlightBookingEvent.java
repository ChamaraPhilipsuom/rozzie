package org.rozzie.processor.events;

import org.rozzie.processor.utils.Constants;

/**
 * Created by chamarap on 5/21/17.
 */
public class FlightBookingEvent extends AirlineEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public FlightBookingEvent(Object source) {
        super(source);
        this.setEventName(Constants.EventName.FLIGHT_BOOKING);
    }
}
