package org.demo.paho.steps;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Step2Publish {

	public static void main(String[] args) throws MqttException, InterruptedException {

		String clientId = UUID.randomUUID().toString() ;		
		//MqttClient client = new MqttClient("tcp://iot.eclipse.org:1883", clientId);
		MqttClient client = new MqttClient("tcp://localhost:1883", clientId);
		
		client.connect();
		System.out.println("Connected");

		for ( int i = 1 ; i <= 10 ; i++ ) {
			
			System.out.println("#" + i + " : sending message...");
			
			String msg = "Hello " + i ;
			
			// New message
			MqttMessage mqttMessage = new MqttMessage(msg.getBytes());

			// Publish 
			client.publish("jug/nantes", mqttMessage);

			// Wait
			System.out.println("#" + i + " : sleeping...");
			Thread.sleep(2000);

			// Message options
			//mqttMessage.setQos(0);
			//mqttMessage.setRetained(false);
			//mqttMessage.setId(messageId);
			
		}

		client.disconnect();
		System.out.println("Disconnected");
		
		client.close(); // Arret des threads
			
	}

//	MqttConnectOptions options ;
//	client.connect(options);
	
}
