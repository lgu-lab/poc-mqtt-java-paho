package org.demo.paho;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Publisher implements AutoCloseable {

	private final static int      DEFAULT_QOS = 0;
	private final static boolean  DEFAULT_RETAINED = false ;
	
//	private final String serverURI ;
	private final String topic ;
	private final String clientId ;
	private final IMqttClient client ;
	
	public Publisher(String serverURI, String topic) throws MqttException {
		super();
//		this.serverURI = serverURI;
		this.topic = topic;
		this.clientId = UUID.randomUUID().toString() ;
		this.client = new MqttClient(serverURI, clientId);
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

	@Override
	public void close() { // throws Exception { // throws MqttException {
		try {
			if ( client.isConnected() ) {
				client.disconnect();
			}
			client.close(); // Cannot re-connect
		} catch (MqttException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void publish(String message) throws MqttException {
		publish(message, DEFAULT_QOS, DEFAULT_RETAINED);
	}
	
	public void publish(String message, int qos ) throws MqttException {
		publish(message, qos, DEFAULT_RETAINED);
	}
	
	public void publish(String message, boolean retained ) throws MqttException {
		publish(message, DEFAULT_QOS, retained);
	}
	
	public void publish(String message, int qos, boolean retained) throws MqttException {
		
		// Message creation
		byte[] payload = message.getBytes();
		MqttMessage mqttMessage = new MqttMessage(payload);

		// Message options
		mqttMessage.setQos(qos);
		mqttMessage.setRetained(retained);
		//mqttMessage.setId(messageId);
		
		// Check if connected 
		if ( ! client.isConnected() ) {
			// Connection 
			client.connect(getOptions());
		}

		// Publish 
		client.publish(topic, mqttMessage);
	}

}
