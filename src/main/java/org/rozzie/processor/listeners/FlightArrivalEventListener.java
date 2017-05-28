package org.rozzie.processor.listeners;

import org.kie.internal.KnowledgeBase;
import org.kie.internal.runtime.StatelessKnowledgeSession;
import org.rozzie.processor.events.AirlineEvent;
import org.rozzie.processor.events.FlightArrivalTimeChangeEvent;
import org.rozzie.processor.utils.Util;


public class FlightArrivalEventListener extends AirlineListener {
    public void changeReceived(AirlineEvent event) {
        if (event instanceof FlightArrivalTimeChangeEvent) {

        }
    }
}
