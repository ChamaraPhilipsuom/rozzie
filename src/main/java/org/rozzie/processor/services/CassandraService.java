package org.rozzie.processor.services;

import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.rozzie.processor.exceptions.WrongKeyException;
import org.rozzie.processor.exceptions.WrongSchemaException;
import org.rozzie.processor.models.Schema;
import org.rozzie.processor.repositories.CassandraConnector;
import org.rozzie.processor.utils.Constants;
import org.rozzie.processor.utils.DBUtils;
import org.rozzie.processor.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Service
@EnableCassandraRepositories("org.rozzie.processor.repositories")
public class CassandraService {

	@Autowired
	private CassandraConnector cassandraDB;

	@PostConstruct
	private void init() {
		String cassandrsIP = DBUtils.getProperty(Constants.PropertyKeys.CASSANDRA_INSTANCE_IP);
		String port = DBUtils.getProperty(Constants.PropertyKeys.CASSANDRA_PORT);
		this.cassandraDB.connect(cassandrsIP, Integer.parseInt(port));
	}

	public void createDBSchema(String dbSchema, String messageType) throws WrongSchemaException {
		saveDBSchemaFile(dbSchema,messageType);
		Schema schema = Util.getSchema(messageType);
		cassandraDB.getSession().execute(DBUtils.getCreateTableCQL(schema));
	}

	public String saveDBSchemaFile(String dbSchema, String messageType) {
		String fileLocation = "/home/chamara/Desktop/docs/rozzie/schemas/" + messageType + "-schema.properties";
		Util.saveFile(dbSchema,fileLocation);
		return fileLocation;
	}

	public JSONObject validateJSON(String json, String messageType) {

		JSONObject object = null;
		try {
			InputStream inputStream = new FileInputStream(
					"/home/chamara/Desktop/docs/rozzie/schemas/" + messageType + "-model.json");
			JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
			org.everit.json.schema.Schema schema = SchemaLoader.load(rawSchema);
			InputStream jsonStream = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
			object = new JSONObject(new JSONTokener(jsonStream));
			schema.validate(object);
		} catch (ValidationException | FileNotFoundException e) {
			System.out.println("Exception caught");
		}
		return object;
	}

	public void saveJSON(String json, String messageType) throws WrongSchemaException, WrongKeyException {
        JSONObject object = validateJSON(json, messageType);
		Schema schema = Util.getSchema(messageType);
		JSONObject savingObject = Util.getJSONDAO(schema, object);
		cassandraDB.getSession().execute(DBUtils.getSaveJSONCQL(schema.getTableName(),savingObject));
	}

}
