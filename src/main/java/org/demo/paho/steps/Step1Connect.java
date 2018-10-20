package org.demo.paho.steps;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Step1Connect {

	public static void main(String[] args) throws MqttException, InterruptedException {

		String clientId = UUID.randomUUID().toString() ;		
		MqttClient client = new MqttClient("tcp://iot.eclipse.org:1883", clientId);
		
		client.connect();
		System.out.println("Connected");
		
		System.out.println("Sleeping...");
		Thread.sleep(4000);
		
		client.disconnect();
		System.out.println("Disconnected");
		
		client.close(); // Arret des threads
			
	}

//	MqttConnectOptions options ;
//	client.connect(options);
	
}
