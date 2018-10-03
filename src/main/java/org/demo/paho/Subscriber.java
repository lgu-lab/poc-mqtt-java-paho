package org.demo.paho;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscriber implements AutoCloseable{
	
	private final String topicFilter ;
	private final String clientId ;
	private final IMqttClient client ;
	//private final MqttCallback callback ;
	
	public Subscriber(String serverURI, String topicFilter, MqttCallback callback ) throws MqttException {
		super();
		this.topicFilter = topicFilter;
		this.clientId = UUID.randomUUID().toString() ;
		this.client = new MqttClient(serverURI, clientId);
		//this.callback = callback ;
		this.client.setCallback(callback);
	}
	
	private MqttConnectOptions getOptions() {
		// Connect options
		MqttConnectOptions connectOptions = new MqttConnectOptions();
		connectOptions.setAutomaticReconnect(true);
		connectOptions.setCleanSession(true);
		connectOptions.setConnectionTimeout(10);
		// connectOptions.setUserName(userName);
		// connectOptions.setPassword(password);
		return connectOptions;
	}

	public boolean isConnected() {
		return client.isConnected(); 
	}

	public void subscribe() throws MqttException {
			
			// Check if connected 
			if ( ! client.isConnected() ) {
				// Connection 
				client.connect(getOptions());
			}
			
			// Subscribe 
			client.subscribe(topicFilter);
			
	}
	
	@Override
	public void close() { 
		try {
			if ( client.isConnected() ) {
				client.disconnect();
			}
			client.close(); // Cannot re-connect
		} catch (MqttException e) {
			throw new RuntimeException(e);
		}
	}

}
