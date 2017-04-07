package org.rozzie.processor.listeners;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.rozzie.processor.events.AirlineEvent;
import org.rozzie.processor.events.FlightDepatureTimeChangeEvent;
import org.rozzie.processor.models.Flight;

/**
 * Created by chamarap on 4/6/17.
 */
public class FlightDepartureEventListener implements AirlineListener {

    public void changeReceived(AirlineEvent event){
        FlightDepatureTimeChangeEvent fEvent = (FlightDepatureTimeChangeEvent)event;
        Flight flight = (Flight) event.getSource();
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("departure-delay-rules");

        kSession.insert(flight);
        kSession.fireAllRules();

    }
}
