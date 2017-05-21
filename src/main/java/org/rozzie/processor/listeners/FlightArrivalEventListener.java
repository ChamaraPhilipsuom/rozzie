package org.rozzie.processor.listeners;

import org.kie.internal.KnowledgeBase;
import org.kie.internal.runtime.StatelessKnowledgeSession;
import org.rozzie.processor.events.AirlineEvent;
import org.rozzie.processor.events.FlightArrivalTimeChangeEvent;
import org.rozzie.processor.models.event.sources.Flight;
import org.rozzie.processor.utils.Util;

/**
 * Created by chamarap on 5/11/17.
 */
public class FlightArrivalEventListener extends AirlineListener {
    public void changeReceived(AirlineEvent event) {
        if (event instanceof FlightArrivalTimeChangeEvent) {
            Flight flight = (Flight) event.getSource();
            try {
                KnowledgeBase knowledgeBase = Util.createKnowledgeBaseFromSpreadsheet(((FlightArrivalTimeChangeEvent)
                        event).getEventName());
                StatelessKnowledgeSession session = knowledgeBase.newStatelessKnowledgeSession();
                session.execute(flight);
            } catch (Exception e) {
                System.out.println("Exception occured " + e);
            }
        }
    }
}
