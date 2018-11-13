package org.demo.paho.steps;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class StepXPublishQOS2 {

	public static void main(String[] args) throws MqttException, InterruptedException {

		String clientId = UUID.randomUUID().toString() ;		

		MqttClient client = new MqttClient("tcp://localhost:1883", clientId);
		
		client.connect();
		System.out.println("Connected");

		for ( int i = 1 ; i <= 10 ; i++ ) {
			
			System.out.println("#" + i + " : sending message...");
			
			String msg = "Hello " + i ;
			
			// New message
			MqttMessage mqttMessage = new MqttMessage(msg.getBytes());
			// Message options 
			mqttMessage.setQos(2);
			// Publish 
			client.publish("jug/nantes", mqttMessage);
			
			// client.publish("jug/nantes", msg.getBytes(), 2, false);

			// Wait
			System.out.println("#" + i + " : sleeping...");
			Thread.sleep(2000);

			
		}

		client.disconnect();
		System.out.println("Disconnected");
		
		client.close(); // Arret des threads
			
	}

//	MqttConnectOptions options ;
//	client.connect(options);
	
}
