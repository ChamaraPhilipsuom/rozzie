package org.rozzie.processor.utils;

/**
 * Created by chamarap on 5/14/17.
 */
public class Constants {

	public static final String EVENT_MESSAGE = "=============================================\n"+
			"        ROZZIE EVENT TRIGGERED\n" + "=============================================\n";

	public static final String SCHEMA_KEY_PROPERTY = "key";
	public static final String DEFAULT_KEY = "receivedTime";
	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm";

	public static class PropertyKeys{
		public static final String CASSANDRA_KEYSPACE = "spring.data.cassandra.keyspace-name";
		public static final String CASSANDRA_INSTANCE_IP = "spring.data.cassandra.instance-ip";
		public static final String CASSANDRA_PORT = "spring.data.cassandra.instance-port";
	}

	public static class RequestUri {

		public static final String GET = "/info";
		public static final String CREATE = "/create";
		public static final String PROCESS_JSON = "/process";
		
		public static class Rozzie {
			public static final String CONTROLLER = "/rozzie";
			public static final String CHANGE_DEPATURE = "/changeDepature";
			public static final String CHANGE_ARRIVAL = "/changeArrival";
			public static final String TAKE_OFF = "/takeOff";
		}
		public static class Schema {
			public static final String CONTROLLER = "/schema";
			public static final String CREATE_JSON_SCHEMA = "/createJSONSchema";
			public static final String CREATE_DB_SCHEMA = "/createDBSchema";
			public static final String CREATE_RULE = "/createRule";
		}


	}

	public static class EventName {
		public static final String FLIGHT_DEPATURE_TIMECHANGE = "depatureTimeChange";
		public static final String FLIGHT_ARRIVAL_TIMECHANGE = "arrivalTimeChange";
		public static final String FLIGHT_BOOKING = "bookingEvent";
	}
}
