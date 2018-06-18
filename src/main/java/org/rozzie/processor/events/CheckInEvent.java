package org.rozzie.processor.events;


public class CheckInEvent extends  AirlineEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CheckInEvent(Object source) {
        super(source);
    }
}
