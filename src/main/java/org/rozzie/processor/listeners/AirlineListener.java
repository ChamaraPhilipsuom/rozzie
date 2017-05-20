package org.rozzie.processor.listeners;

import org.rozzie.processor.events.AirlineEvent;

/**
 * Created by chamarap on 4/7/17.
 */
public abstract class AirlineListener {

	public abstract void changeReceived(AirlineEvent event);
}
