package org.rozzie.processor.listeners;

import org.kie.internal.KnowledgeBase;
import org.kie.internal.runtime.StatelessKnowledgeSession;
import org.rozzie.processor.utils.Util;
import org.rozzie.processor.events.AirlineEvent;
import org.rozzie.processor.events.FlightDepatureTimeChangeEvent;

public class FlightDepartureEventListener extends AirlineListener {

	public void changeReceived(AirlineEvent event) {
		if (event instanceof FlightDepatureTimeChangeEvent) {
		}
	}
}
