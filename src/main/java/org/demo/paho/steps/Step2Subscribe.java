package org.demo.paho.steps;

import java.io.IOException;
import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Step2Subscribe {

	public static void main(String[] args) throws MqttException, InterruptedException {

		String clientId = UUID.randomUUID().toString() ;		
		//MqttClient client = new MqttClient("tcp://iot.eclipse.org:1883", clientId);
		MqttClient client = new MqttClient("tcp://localhost:1883", clientId);
		
		client.connect();
		System.out.println("Connected");

//		System.out.println("Setting callback...\n");
//		client.setCallback(new SubscriberPrintCallback());
//		System.out.println("Callback set.\n");
//		
//		System.out.println("Subscribing...\n");
//		client.subscribe("jug/nantes");
//		System.out.println("Subscribe OK.\n");
//		
//		System.out.println("Waiting user input to stop...\n");
//		waitUserInput();

		// Set callback
		client.setCallback(new SubscriberPrintCallback());
		
		// Subscribe
		client.subscribe("jug/nantes");
		
		// Wait
		waitUserInput();
		
		
		client.disconnect();
		System.out.println("Disconnected");
		
		client.close(); // Arret des threads
			
	}

	public static void waitUserInput() {
		try {
			System.in.read();
			System.out.println("--- USER INPUT ---");			
		} catch (IOException e) {
			//If we can't read we'll just exit
		}
	}
	
}
