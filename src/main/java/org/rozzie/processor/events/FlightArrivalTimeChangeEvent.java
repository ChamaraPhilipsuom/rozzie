package org.rozzie.processor.events;

import org.rozzie.processor.utils.Constants;

/**
 * Created by chamarap on 5/11/17.
 */
public class FlightArrivalTimeChangeEvent extends AirlineEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public FlightArrivalTimeChangeEvent(Object source) {
        super(source);
        this.setEventName(Constants.EventName.FLIGHT_ARRIVAL_TIMECHANGE);
    }
}
