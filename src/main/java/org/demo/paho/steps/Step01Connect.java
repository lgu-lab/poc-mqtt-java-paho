package org.demo.paho.steps;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Step01Connect {

	public static void main(String[] args) throws MqttException, InterruptedException {

		// String clientId = UUID.randomUUID().toString() ;		
		String clientId = "ClientIdstep1" ;		
		MqttClient client = new MqttClient("tcp://iot.eclipse.org:1883", clientId);
		
		client.connect();

//		// Connect options
//		MqttConnectOptions connectOptions = new MqttConnectOptions();
//		connectOptions.setAutomaticReconnect(true);
//		connectOptions.setCleanSession(false);
//		connectOptions.setConnectionTimeout(10);
//
//		client.connect(connectOptions);
		
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
