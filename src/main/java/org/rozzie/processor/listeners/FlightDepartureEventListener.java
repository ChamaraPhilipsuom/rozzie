package org.rozzie.processor.listeners;

import org.kie.internal.KnowledgeBase;
import org.kie.internal.runtime.StatelessKnowledgeSession;
import org.rozzie.processor.utils.Util;
import org.rozzie.processor.events.AirlineEvent;
import org.rozzie.processor.events.FlightDepatureTimeChangeEvent;
import org.rozzie.processor.models.event.sources.Flight;

public class FlightDepartureEventListener extends AirlineListener {

	public void changeReceived(AirlineEvent event) {
		if (event instanceof FlightDepatureTimeChangeEvent) {
			Flight flight = (Flight) event.getSource();
			try {
				KnowledgeBase knowledgeBase = Util.createKnowledgeBaseFromSpreadsheet(event.getEventName());
				StatelessKnowledgeSession session = knowledgeBase.newStatelessKnowledgeSession();
				session.execute(flight);
			} catch (Exception e) {
				System.out.println("Exception occured " + e);
			}
		}
	}
}
