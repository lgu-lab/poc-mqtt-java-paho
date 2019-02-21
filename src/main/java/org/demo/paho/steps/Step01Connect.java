package org.demo.paho.steps;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Step01Connect {

	//private final static String SERVER_URI = "tcp://localhost:1883" ;
	private final static String CLIENT_ID  = "ClientIdTestConnection" ;

	public static void main(String[] args) throws MqttException, InterruptedException {

		MqttClient client = new MqttClient(Config.SERVER_URI, CLIENT_ID);
		
		client.connect();

//		// Connect options
//		MqttConnectOptions connectOptions = new MqttConnectOptions();
//		connectOptions.setAutomaticReconnect(true);
//		connectOptions.setCleanSession(false);
//		connectOptions.setConnectionTimeout(10);
//
//		client.connect(connectOptions);
		
		System.out.println("Connected");
		// datastore creation (.lck)
		
		// Wait
		System.out.println("Waiting for user input...");
		Tooling.waitUserInput();
		
		client.disconnect();
		System.out.println("Disconnected");
		
		client.close(); // Arret des threads + clean datastore (.lck)		
	}
}
