package org.rozzie.processor.utils;

/**
 * Created by chamarap on 5/14/17.
 */
public class Constants {

	public static class RequestUri {

		public static final String GET = "/info";
		public static final String CREATE = "/create";

		public static class AirPort {
			public static final String CONTROLLER = "/port";
		}
		public static class Baggage {
			public static final String CONTROLLER = "/bag";
		}
		public static class Flight {
			public static final String CONTROLLER = "/flight";
			public static final String CHANGE_DEPATURE = "/changeDepature";
		}
		public static class Passenger {
			public static final String CONTROLLER = "/passenger";
		}
		public static class Plane {
			public static final String CONTROLLER = "/plane";
		}

	}

	public static class EventName {
		public static final String FLIGHT_DEPATURE_TIMECHANGE = "depatureTimeChange";
	}
}
