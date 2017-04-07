package org.rozzie.processor.events;

import java.util.EventObject;

/**
 * Created by chamarap on 4/7/17.
 */
public abstract  class AirlineEvent extends EventObject{


    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public AirlineEvent(Object source) {
        super(source);
    }
}
