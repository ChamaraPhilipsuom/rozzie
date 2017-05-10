package org.rozzie.processor.listeners;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.runtime.StatelessKnowledgeSession;
import org.rozzie.processor.Util;
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
        /*
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("departure-delay-rules");

        kSession.insert(flight);
        kSession.fireAllRules();
        */
        try {
            KnowledgeBase knowledgeBase = Util.createKnowledgeBaseFromSpreadsheet();
            StatelessKnowledgeSession session = knowledgeBase.newStatelessKnowledgeSession();
            session.execute(flight);
        } catch (Exception e) {
            System.out.println("Exception occured " + e );
        }



    }
}
