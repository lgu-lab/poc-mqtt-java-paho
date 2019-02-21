package org.demo.paho.steps;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Step12SubQoS2 {

	private final static int    QOS        = 2 ;
	private final static String CLIENT_ID  = "ClientId-SUB-QoS-" + QOS ;
	
	public static void main(String[] args) throws MqttException, InterruptedException {

		MqttClient client = new MqttClient(Config.SERVER_URI, CLIENT_ID);
		
		System.out.println("Connecting...");
		System.out.println(" . Client ID  : " + CLIENT_ID );
		System.out.println(" . Server URI : " + Config.SERVER_URI );
		client.connect();
		System.out.println("Connected.");

		// Set callback
		client.setCallback(new SubscriberPrintCallback());
		
		// Subscribe
		System.out.println("Subscribing with QoS = " + QOS );
		client.subscribe(Config.TOPIC, QOS); // Quality of Service
		
		// Wait
		System.out.println("Waiting for user input...");
		Tooling.waitUserInput();
		
		client.disconnect();
		System.out.println("Disconnected");
		
		client.close(); // Arret des threads
	}
}
