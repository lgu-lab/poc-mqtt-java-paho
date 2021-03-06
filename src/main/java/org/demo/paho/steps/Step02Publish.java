package org.demo.paho.steps;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Step02Publish {

	private final static String CLIENT_ID  = "ClientId-DEMO-PUB" ;
//	private final static String SERVER_URI = "tcp://localhost:1883" ;
//	private final static String TOPIC      = "a/b/c" ;
	
	public static void main(String[] args) throws MqttException, InterruptedException {

		MqttClient client = new MqttClient(Config.SERVER_URI, CLIENT_ID);		
		
		client.connect(); // Sans option
		System.out.println("Connected");

		for ( int i = 1 ; i <= 500 ; i++ ) {
			
			// New message
			String msg = "Hello Brest " + i + " (from Java app)";
			MqttMessage mqttMessage = new MqttMessage(msg.getBytes());

			System.out.println("#" + i + " : Publishing message : " + msg + " (topic = '" + Config.TOPIC + "' )" );
			// Publish 
			client.publish(Config.TOPIC, mqttMessage);
			
			// Wait
			Thread.sleep(1000);
		}

		client.disconnect();
		System.out.println("Disconnected");
		
		client.close(); // Arret des threads
	}
}
