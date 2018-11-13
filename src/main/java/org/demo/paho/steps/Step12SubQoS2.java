package org.demo.paho.steps;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Step12SubQoS2 {

	private final static int    QOS        = 2 ;
	private final static String SERVER_URI = "tcp://localhost:1883" ;
	private final static String CLIENT_ID  = "ClientId-SUB-QoS-" + QOS ;
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
		System.out.println("Subscribing with QoS = " + QOS );
		client.subscribe(TOPIC, QOS); // Quality of Service
		
		// Wait
		System.out.println("Waiting for user input...");
		Tooling.waitUserInput();
		
		client.disconnect();
		System.out.println("Disconnected");
		
		client.close(); // Arret des threads
	}
}
