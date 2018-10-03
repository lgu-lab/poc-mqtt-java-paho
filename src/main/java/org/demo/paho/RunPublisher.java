package org.demo.paho;

import org.eclipse.paho.client.mqttv3.MqttException;

public class RunPublisher {
	
	//private final static String SERVER_URI = "tcp://iot.eclipse.org:1883" ;
	private final static String SERVER_URI = "tcp://localhost:1883" ;
	
	private final static String TOPIC = "/a/b/c" ;
	
	public static void main(String[] args) {
		
		System.out.println("PUBLISHER : Server = " + SERVER_URI + "  (topic : " + TOPIC + ") \n" );
		
		HelloPahoPublisher publisher = null ;
		try {
			publisher = new HelloPahoPublisher(SERVER_URI, TOPIC);
			System.out.println("Publisher created. \n");

			System.out.println("Connected ? : " + publisher.isConnected());
			publisher.publish("Hello #1");
			System.out.println("Message published. \n");

			System.out.println("Connected ? : " + publisher.isConnected());
			publisher.publish("Hello #2");
			System.out.println("Message published. \n");

			System.out.println("Connected ? : " + publisher.isConnected());
			//publisher.publish("Hello 3", 2, true);
			publisher.publish("Hello #3");
			System.out.println("Message published. \n");

//			System.out.println("Connected ? : " + publisher.isConnected());
//			//publisher.publish("Hello 3", 2, true);
//			publisher.publish("RETAINED MESSAGE", true);
//			System.out.println("Message published. \n");

			

			
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Closing...");
			if ( publisher != null ) {
				try {
					publisher.close();
					System.out.println("Closed.");
				} catch (MqttException e) {
					System.out.println("Cannot close :-(  ");
				}
			}
		}
		System.out.println("END");

	}
}
