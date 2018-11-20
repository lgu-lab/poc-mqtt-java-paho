package org.demo.paho.steps;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Step11PubQoSDefault {

	private final static String SERVER_URI = "tcp://localhost:1883" ;
	private final static String CLIENT_ID  = "ClientId-PUB-QoS-default" ;
	private final static String TOPIC      = "jug/nantes" ;
	
	public static void main(String[] args) throws MqttException, InterruptedException {

		MqttClient client = new MqttClient(SERVER_URI, CLIENT_ID);		
		
		client.connect();
		// MqttConnectOptions options ;
		// client.connect(options);

		System.out.println("Connected");

		for ( int i = 1 ; i <= 500 ; i++ ) {
			
			// New message
			String msg = "Hello Nantes " + i ;
			MqttMessage mqttMessage = new MqttMessage(msg.getBytes());
			// Message options
			//mqttMessage.setQos(0);
			//mqttMessage.setRetained(false);
			//mqttMessage.setId(messageId);

			System.out.println("#" + i + " : Publishing message (default QoS) : " + msg + " (topic = '" +TOPIC+"' )" );
			
			// Publish 
			client.publish(TOPIC, mqttMessage);
			
			// Wait
			// System.out.println("#" + i + " : sleeping...");
			Thread.sleep(1000);
		}

		client.disconnect();
		System.out.println("Disconnected");
		
		client.close(); // Arret des threads
	}
}
