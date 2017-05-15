package org.rozzie.processor.utils;

import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.rozzie.processor.models.dao.FlightDAO;
import org.rozzie.processor.models.dto.FlightDTO;

/**
 * Created by chamarap on 5/11/17.
 */
public class Util {

    public static KnowledgeBase createKnowledgeBaseFromSpreadsheet() throws Exception {
        DecisionTableConfiguration dtconf = KnowledgeBuilderFactory.newDecisionTableConfiguration();
        dtconf.setInputType(DecisionTableInputType.XLS);

        KnowledgeBuilder knowledgeBuilder = KnowledgeBuilderFactory
                .newKnowledgeBuilder();
        knowledgeBuilder.add(ResourceFactory.newClassPathResource("flight.xls"),
                ResourceType.DTABLE, dtconf);

        if (knowledgeBuilder.hasErrors()) {
            throw new RuntimeException(knowledgeBuilder.getErrors().toString());
        }

        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(knowledgeBuilder
                .getKnowledgePackages());
        return knowledgeBase;
    }
}
