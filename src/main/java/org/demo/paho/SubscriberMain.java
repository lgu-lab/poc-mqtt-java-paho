package org.demo.paho;

import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttException;

public class SubscriberMain {
	
	private final static String SERVER_URI = "tcp://iot.eclipse.org:1883" ;
	//private final static String SERVER_URI = "tcp://localhost:1883" ;
	
	private final static String TOPIC = "/a/b/c" ;

	public static void main(String[] args) {
		
		try ( Subscriber subscriber = new Subscriber(SERVER_URI, TOPIC, new SubscriberCallback()) ) {
			System.out.println("Subscriber created. \n");

			System.out.println("Connected ? : " + subscriber.isConnected());

			System.out.println("Start subscribe\n");
			subscriber.subscribe();
			System.out.println("Subscribe OK... \n");
			
			waitUserInput();
			
		} catch (MqttException e) {
			e.printStackTrace();
		}
		System.out.println("END");

		
	}
	
	public static void waitUserInput() {
		try {
			System.in.read();
		} catch (IOException e) {
			//If we can't read we'll just exit
		}
	}
}
