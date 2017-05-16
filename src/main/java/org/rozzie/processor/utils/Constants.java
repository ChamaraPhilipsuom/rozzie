package org.rozzie.processor.utils;

/**
 * Created by chamarap on 5/14/17.
 */
public class Constants {

	public static class RequestUri {

	     public static class Flight {
             public static final String GET_FLIGHT = "/info";
             public static final String CHANGE_DEPATURE = "/changeDepature";
         }
         public static class Port {
             public static final String GET_PORT = "/info";
         }

	}

	public static class EventName {
	    public static final String FLIGHT_DEPATURE_TIMECHANGE = "depatureTimeChange";
    }
}
