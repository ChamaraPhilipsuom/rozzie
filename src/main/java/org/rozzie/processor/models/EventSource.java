package org.rozzie.processor.models;

import org.rozzie.processor.listeners.AirlineListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chamarap on 4/6/17.
 */
public abstract class EventSource {

	private List<AirlineListener> _listeners = new ArrayList();

	public synchronized void addListener(AirlineListener listener) {
		_listeners.add(listener);
	}

	public synchronized void removeListener(AirlineListener listener) {
		_listeners.remove(listener);
	}

	public List<AirlineListener> getListeners() {
		return _listeners;
	}
}