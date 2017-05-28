package org.rozzie.processor.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.DecisionTableConfiguration;
import org.kie.internal.builder.DecisionTableInputType;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.rozzie.processor.exceptions.WrongKeyException;
import org.rozzie.processor.exceptions.WrongSchemaException;
import org.rozzie.processor.models.Schema;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

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

		return false;
	}

	public static boolean isNull(Object object) {
		return !isNotNull(object);
	}

	public JSONObject getObject(String key, JSONObject input) throws WrongKeyException {
		if(!key.startsWith("\\")){
			throw new WrongKeyException("Key doesn't start with \\");
		}
		JSONObject requiredValue = input;
		key = key.replaceFirst("\\\\","");
		String[] keySet = key.split(".");
		for(String innerKey: keySet){
			if(requiredValue.has(innerKey)){
				throw new WrongKeyException("No property with name " + innerKey);
			}
			requiredValue = requiredValue.getJSONObject(innerKey);
		}
		return requiredValue;
	}

	public Schema getSchema(String fileLocation) throws WrongSchemaException {
		Properties props = new Properties();
		Schema schema = new Schema();
		InputStream propStream = null;
		try {
			propStream = new FileInputStream(fileLocation);
			props.load(propStream);
			String concatinatedKey = props.getProperty(Constants.SCHEMA_KEY_PROPERTY);
			String[] privatekeys = concatinatedKey.split("\\+");
			schema.setKeys(new ArrayList<String>(Arrays.asList(privatekeys)));
			props.remove(Constants.SCHEMA_KEY_PROPERTY);
			schema.setDataFieldMapping(props);
//			schema.setTableName(fileLocation.split("//"));
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

	public JSONObject getJSONDAO(Schema schema, JSONObject input) throws WrongKeyException{
		JSONObject json = new JSONObject();
		Properties properties = schema.getDataFieldMapping();
		for(Object keyObj : properties.keySet()){
			String key = (String)keyObj;
			json.put(key,getObject(properties.getProperty(key),input));
		}
		return json;
	}
}
