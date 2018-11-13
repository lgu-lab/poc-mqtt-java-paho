package org.demo.paho.steps;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Step10PubQoS0 {

	private final static int    QOS        = 0 ;
	private final static String SERVER_URI = "tcp://localhost:1883" ;
	private final static String CLIENT_ID  = "ClientId-PUB-QoS-" + QOS ;
	private final static String TOPIC      = "jug/nantes" ;
	
	public static void main(String[] args) throws MqttException, InterruptedException {

		MqttClient client = new MqttClient(SERVER_URI, CLIENT_ID);
		
		client.connect();
		System.out.println("Connected");

		for ( int i = 1 ; i <= 500 ; i++ ) {
			
			// New message
			String msg = "Hello " + i ;
			MqttMessage mqttMessage = new MqttMessage(msg.getBytes());

			// Message options
			mqttMessage.setQos(QOS);
			//mqttMessage.setRetained(false);
			//mqttMessage.setId(messageId);
			
			// Publish with MqttMessage instance
			System.out.println("#" + i + " : Publishing message (QoS = " + QOS + ") : " + msg );
			client.publish(TOPIC, mqttMessage);
			
			// Publish without MqttMessage instance
			// client.publish(TOPIC, msg.getBytes(), 0, false);
			
			// Wait
			// System.out.println("#" + i + " : sleeping...");
			Thread.sleep(1000);
		}

		client.disconnect();
		System.out.println("Disconnected");
		
		client.close(); // Arret des threads
	}
}
