package org.rozzie.processor.services;

import com.datastax.driver.core.Row;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.kie.api.runtime.KieSession;
import org.rozzie.processor.repositories.CassandraConnector;
import org.rozzie.processor.utils.Constants;
import org.rozzie.processor.utils.DBUtils;
import org.rozzie.processor.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Service
public class RuleService {

    @Autowired
    private CassandraConnector cassandraDB;

    @PostConstruct
    private void init() {
        String cassandrsIP = DBUtils.getProperty(Constants.PropertyKeys.CASSANDRA_INSTANCE_IP);
        String port = DBUtils.getProperty(Constants.PropertyKeys.CASSANDRA_PORT);
        this.cassandraDB.connect(cassandrsIP, Integer.parseInt(port));
    }

	public void createRules(String rules, String messageType) {
		String fileLocation = "/home/chamara/Desktop/docs/rozzie/schemas/" + messageType + "-rules.drl";
		StringBuilder ruleBuilder = new StringBuilder(
				"package com.rozzie.processor\n" + "\n" + "import org.rozzie.processor.utils.Util\n"
						+ "import java.time.Duration;\n" + "import org.json.JSONObject;\n");
		rules = ruleBuilder.append(rules).toString();
		Util.saveFile(rules, fileLocation);
	}

	public void runRulesFortheMessage(String json, String messageType){

	    JSONObject newRecord = null;
	    JSONObject oldRecord = null;
        String getRecords = DBUtils.getLastTwoRecordsCQL(messageType, newRecord);
        List<Row> rows = cassandraDB.getSession().execute(getRecords).all();
        String newRecordStr = rows.get(0).getString("[json]");
        String oldRecordStr = rows.get(1).getString("[json]");
        InputStream jsonStream = new ByteArrayInputStream(newRecordStr.getBytes(StandardCharsets.UTF_8));
        newRecord = new JSONObject(new JSONTokener(jsonStream));
        jsonStream = new ByteArrayInputStream(oldRecordStr.getBytes(StandardCharsets.UTF_8));
        oldRecord = new JSONObject(new JSONTokener(jsonStream));
	    applyRules(messageType, newRecord, oldRecord);
    }

    public void applyRules(String messageType, JSONObject newRecord, JSONObject oldRecord){
	    try {
            String fileLocation = "/home/chamara/Desktop/docs/rozzie/schemas/" + messageType + "-rules.drl";
            KieSession kSession = Util.getKieSession(fileLocation);
            kSession.insert(newRecord);
            kSession.fireAllRules();
        } catch (Exception e) {
            System.out.println("Exception occured while applying rules");
        }

    }

}
