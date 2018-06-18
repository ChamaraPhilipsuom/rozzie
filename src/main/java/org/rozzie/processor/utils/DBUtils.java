package org.rozzie.processor.utils;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.rozzie.processor.exceptions.WrongSchemaException;
import org.rozzie.processor.models.Schema;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

@Configuration
public class DBUtils {


	private static Properties properties = getEnvProperties();

	private static Properties getEnvProperties(){
		Properties props = new Properties();
		InputStream propStream = null;
		String fileLocation = "/home/chamara/Desktop/docs/rozzie/rozzie/src/main/resources/application.properties";
		try {
			propStream = new FileInputStream(fileLocation);
			props.load(propStream);
		} catch (IOException ex) {
			System.out.println( "The props file cannot be read" );
		} finally {
			if (propStream != null) {
				try {
					propStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return props;
	}

	public static String getProperty(String key){
		return properties.getProperty(key);
	}
	public static String getCreateTableCQL(Schema schema) {

		String keySpace = properties.getProperty(Constants.PropertyKeys.CASSANDRA_KEYSPACE);
		StringBuilder query = new StringBuilder("CREATE TABLE IF NOT EXISTS " + keySpace + "." + schema.getTableName()
				.trim() +
				"(");
		for (String columnName : schema.getColumns()) {
			query.append(columnName.trim() + " text,");
		}
		query.append("PRIMARY KEY (");
		for (String key : schema.getKeys()) {
			query.append(key.trim() + ",");
		}
		query.deleteCharAt(query.length() - 1);
		query.append("))");
		return query.toString();
	}

	public static String getSaveJSONCQL(String tablename, JSONObject json){
		String keySpace = properties.getProperty(Constants.PropertyKeys.CASSANDRA_KEYSPACE);
		StringBuilder query = new StringBuilder("INSERT INTO " + keySpace + "." + tablename + " JSON '" + json
				.toString()
				+"'");
		return query.toString();
	}

	public static String getLastTwoRecordsCQL(String tablename, JSONObject json) {
		String keySpace = properties.getProperty(Constants.PropertyKeys.CASSANDRA_KEYSPACE);

		StringBuilder query = new StringBuilder("SELECT json receivedtime, flight_number, origination_airport_code, " +
				"destination_airport_code, scheduled_departure_time, boarding_time, destination_airport, " +
				"destination_city, destination_gate, destination_terminal, estimated_arrival_time, " +
				"estimated_departure_time, flight_schedule, flight_status, origination_airport, origination_city, " +
				"origination_gate, origination_terminal, scheduled_arrival_time" +
				" FROM rozzieevents.flight;");
		return query.toString();
	}
}
