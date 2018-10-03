package org.demo.paho;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscriber extends MqttAbstractClient {
	
	private final String topicFilter ;
	
	/**
	 * Constructor
	 * @param serverURI
	 * @param topicFilter
	 * @param callback
	 * @throws MqttException
	 */
	public Subscriber(String serverURI, String topicFilter, MqttCallback callback ) throws MqttException {
		super(serverURI);
		this.topicFilter = topicFilter;
		this.client.setCallback(callback);
	}
	
	public void subscribe() throws MqttException {
			
		// Check if connected 
		checkConnected();
			
		// Subscribe 
		client.subscribe(topicFilter);
			
	}
	
}
