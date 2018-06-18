package org.rozzie.processor.events;

/**
 * Created by chamarap on 5/21/17.
 */
public class FlightTakeOffEvent extends AirlineEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public FlightTakeOffEvent(Object source) {
        super(source);
    }
}
