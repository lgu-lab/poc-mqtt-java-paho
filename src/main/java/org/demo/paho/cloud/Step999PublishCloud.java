package org.demo.paho.cloud;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Step999PublishCloud {

	public static void main(String[] args) throws MqttException, InterruptedException {

		String clientId = UUID.randomUUID().toString() ;		
		//MqttClient client = new MqttClient("tcp://iot.eclipse.org:1883", clientId);
		MqttClient client = new MqttClient("tcp://test.mosquitto.org:1883", clientId);
		
		System.out.println("Connecting to " + client.getServerURI());
		client.connect();
		System.out.println("Connected");

		for ( int i = 1 ; i <= 20 ; i++ ) {
			
			System.out.println("#" + i + " : sending message...");
			
			if ( i % 2 == 0 ) {
				// New message
				String msg = "Hello " + i ;
				MqttMessage mqttMessage = new MqttMessage(msg.getBytes());
				// Publish 
				client.publish("jug/nantes/txt", mqttMessage);
			}
			else {
				// New message
				String msg = "" + i  ;
				MqttMessage mqttMessage = new MqttMessage(msg.getBytes());
				// Publish 
				client.publish("jug/nantes/num", mqttMessage);
				
			}

			// Wait
			System.out.println("#" + i + " : sleeping...");
			Thread.sleep(1000);

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
