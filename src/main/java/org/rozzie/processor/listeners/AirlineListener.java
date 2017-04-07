package org.rozzie.processor.listeners;

import org.rozzie.processor.events.AirlineEvent;

/**
 * Created by chamarap on 4/7/17.
 */
public interface AirlineListener {

    public void changeReceived(AirlineEvent event);
}
