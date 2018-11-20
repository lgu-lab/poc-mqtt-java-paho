package org.demo.paho.steps;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Step11SubQoSDefault {

	private final static String SERVER_URI = "tcp://localhost:1883" ;
	private final static String CLIENT_ID  = "ClientId-SUB-QoS-default" ;
	private final static String TOPIC      = "jug/nantes" ;
	
	public static void main(String[] args) throws MqttException, InterruptedException {

		MqttClient client = new MqttClient(SERVER_URI, CLIENT_ID);
		
		System.out.println("Connecting...");
		System.out.println(" . Client ID  : " + CLIENT_ID );
		System.out.println(" . Server URI : " + SERVER_URI );
		client.connect();
		System.out.println("Connected.");

		// Set callback
		client.setCallback(new SubscriberPrintCallback());
		
		// Subscribe
		System.out.println("Subscribing with default QoS : topic = '" + TOPIC + "' ");
		client.subscribe(TOPIC); // QoS = default = 1
		
		// Wait
		System.out.println("Waiting for user input...");
		Tooling.waitUserInput();
		
		client.disconnect();
		System.out.println("Disconnected");
		
		client.close(); // Arret des threads
	}
}
