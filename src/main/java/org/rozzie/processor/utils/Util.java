package org.rozzie.processor.utils;

import org.apache.commons.lang3.StringUtils;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.rozzie.processor.models.dao.cassandra.AirportCas;
import org.rozzie.processor.models.dao.neo.AirportNeo;
import org.rozzie.processor.models.dao.neo.BaggageNeo;
import org.rozzie.processor.models.dao.neo.FlightNeo;
import org.rozzie.processor.models.dao.neo.PassengerNeo;
import org.rozzie.processor.models.dao.neo.PlaneNeo;

/**
 * Created by chamarap on 5/11/17.
 */
public class Util {

	public static KnowledgeBase createKnowledgeBaseFromSpreadsheet(String eventName) throws Exception {
		DecisionTableConfiguration dtconf = KnowledgeBuilderFactory.newDecisionTableConfiguration();
		dtconf.setInputType(DecisionTableInputType.XLS);

		KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		knowledgeBuilder.add(ResourceFactory.newClassPathResource(eventName + ".xls"), ResourceType.DTABLE, dtconf);

		if (knowledgeBuilder.hasErrors()) {
			throw new RuntimeException(knowledgeBuilder.getErrors().toString());
		}

		KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
		knowledgeBase.addKnowledgePackages(knowledgeBuilder.getKnowledgePackages());
		return knowledgeBase;
	}

	public static boolean isNotNull(Object object) {
		if (object != null) {
			if (object instanceof AirportNeo) {
				AirportNeo dao = (AirportNeo) object;
				if (StringUtils.isNotBlank(dao.getAirportId())) {
					return true;
				}

			} else if (object instanceof BaggageNeo) {
				BaggageNeo dao = (BaggageNeo) object;
				if (StringUtils.isNotBlank(dao.getBaggageId())) {
					return true;
				}

			} else if (object instanceof FlightNeo) {
				FlightNeo dao = (FlightNeo) object;
				if (StringUtils.isNotBlank(dao.getFlightId())) {
					return true;
				}
			} else if (object instanceof PassengerNeo) {
				PassengerNeo dao = (PassengerNeo) object;
				if (StringUtils.isNotBlank(dao.getPassengerId())) {
					return true;
				}
			} else if (object instanceof PlaneNeo) {
				PlaneNeo dao = (PlaneNeo) object;
				if (StringUtils.isNotBlank(dao.getPlaneId())) {
					return true;
				}
			} else if (object instanceof AirportCas) {
				AirportCas dao = (AirportCas) object;
				if (dao.getAirportId() != null) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean isNull(Object object) {
		return !isNotNull(object);
	}
}
