package org.rozzie.processor.utils;

import org.drools.core.SessionConfiguration;
import org.json.JSONObject;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.KieSessionConfiguration;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.rozzie.processor.exceptions.DroolsException;
import org.rozzie.processor.exceptions.WrongKeyException;
import org.rozzie.processor.exceptions.WrongSchemaException;
import org.rozzie.processor.models.Schema;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Util {

	public static KnowledgeBase createKnowledgeBaseFromSpreadsheet(String eventName) throws DroolsException {
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

	public static KieSession getKieSession(String filepath) throws Exception{
		KieServices kieServices = KieServices.Factory.get();
		KieFileSystem kfs = kieServices.newKieFileSystem();
		kfs.write(ResourceFactory.newFileResource(new File(filepath)));
		KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
		KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
		KieSessionConfiguration conf=  SessionConfiguration.getDefaultInstance();
		KieSession kSession = kieContainer.newKieSession(conf);
		if (kieBuilder.getResults().hasMessages(Message.Level.ERROR)) {
			List<Message> errors = kieBuilder.getResults().getMessages(Message.Level.ERROR);
			StringBuilder sb = new StringBuilder("Errors:");
			for (Message msg : errors) {
				sb.append("\n  " + msg);
			}
			throw new DroolsException("Drools exception occured. \n " + sb.toString());
		}

//		KieServices ks = KieServices.Factory.get();
//		KieContainer kContainer = ks.getKieClasspathContainer();
//		KieSession kSession = kContainer.newKieSession("departure-delay-rules");
		System.out.println("KieServices built: ");
		return  kSession;
	}

	public static boolean isNotNull(Object object) {

		return false;
	}

	public static boolean isNull(Object object) {
		return !isNotNull(object);
	}

	public static String getObject(String key, JSONObject input) throws WrongKeyException {
		if (!key.startsWith("//")) {
			throw new WrongKeyException("Key doesn't start with //");
		}
		JSONObject requiredValue = input;
		String value = null;
		key = key.replaceFirst("//", "");
		String[] keySet = key.split("/");
		for (int index = 0; index < keySet.length; index++) {
			String innerKey = keySet[index];
			if (!requiredValue.has(innerKey)) {
				throw new WrongKeyException("No property with name " + innerKey);
			}
			if (index == keySet.length - 1) {
				value = requiredValue.get(innerKey).toString();
			} else {
				requiredValue = requiredValue.getJSONObject(innerKey);
			}
		}
		return value;
	}

	public static Schema getSchema(String tableName) throws WrongSchemaException {
		Properties props = new Properties();
		String fileLocation = "/home/chamara/Desktop/docs/rozzie/schemas/[tableName]-schema.properties";
		fileLocation = fileLocation.replace("[tableName]", tableName);
		Schema schema = new Schema();
		InputStream propStream = null;
		try {
			propStream = new FileInputStream(fileLocation);
			props.load(propStream);
			String concatinatedKey = props.getProperty(Constants.SCHEMA_KEY_PROPERTY);
			String[] privatekeys = concatinatedKey.split("\\+");
			schema.addKeys(new ArrayList<String>(Arrays.asList(privatekeys)));
			props.remove(Constants.SCHEMA_KEY_PROPERTY);
			schema.addDataFieldMapping(props);
			schema.setTableName(tableName);
		} catch (IOException ex) {
			throw new WrongSchemaException("Error in loading schema from file " + fileLocation);
		} finally {
			if (propStream != null) {
				try {
					propStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return schema;
	}

	public static JSONObject getJSONDAO(Schema schema, JSONObject input) throws WrongKeyException {
		JSONObject json = new JSONObject();
		Properties properties = schema.getDataFieldMapping();
		json.put(Constants.DEFAULT_KEY, (String) properties.get(Constants.DEFAULT_KEY));
		properties.remove(Constants.DEFAULT_KEY);
		for (Object keyObj : properties.keySet()) {
			String key = (String) keyObj;
			json.put(key, getObject(properties.getProperty(key), input));
		}
		return json;
	}

	public static void saveFile(String content, String fileLocation) {
		BufferedWriter writer = null;
		FileWriter file = null;
		try {
			file = new FileWriter(fileLocation);
			writer = new BufferedWriter(file);
			writer.write(content);
			writer.flush();
			file.close();

		} catch (IOException e) {
			// throw the exception and terminate
		} finally {
			try {
				if (file != null) {
					file.close();
				}
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				// Write the exception no need to terminate
			}
		}

	}

	public static String getTime(){
		LocalDateTime now  = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.TIME_FORMAT);
		String dateText = now.format(formatter);
		return dateText;
	}

	public static boolean timeGreaterThan(Object initialTime, Object secondTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.TIME_FORMAT);
		LocalDateTime dateTime1 = LocalDateTime.parse((String)initialTime, formatter);
		LocalDateTime dateTime2 = LocalDateTime.parse((String)secondTime, formatter);
		return Duration.between(dateTime2, dateTime1).isNegative();
	}
}
