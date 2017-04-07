package org.rozzie.processor.events;

/**
 * Created by chamarap on 4/6/17.
 */
public class FlightDepatureTimeChangeEvent extends AirlineEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public FlightDepatureTimeChangeEvent(Object source) {
        super(source);
    }
}
