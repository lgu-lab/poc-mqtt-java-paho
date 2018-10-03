package org.demo.paho;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Publisher extends MqttAbstractClient {

	private final String topic ;
	
	public Publisher(String serverURI, String topic) throws MqttException {
		super(serverURI);
		this.topic = topic;
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
		checkConnected();

		// Publish 
		getMqttClient().publish(topic, mqttMessage);
	}

}
