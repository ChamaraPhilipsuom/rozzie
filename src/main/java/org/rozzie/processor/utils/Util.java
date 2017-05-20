package org.rozzie.processor.utils;

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
				if (dao.getAirportId() != null) {
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
