package org.demo.paho.cloud;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class PublishInCloudQoS0 {

	private final static int    QOS        = 0 ;
	
	private final static String SERVER_URI = "tcp://test.mosquitto.org:1883" ;
	//private final static String SERVER_URI = "tcp://iot.eclipse.org:1883" ;
	
	private final static String CLIENT_ID  = "ClientId-PUB-CLOUD-" + QOS ;
	
	public static void main(String[] args) throws MqttException, InterruptedException {

		MqttClient client = new MqttClient(SERVER_URI, CLIENT_ID);
		
		client.connect();
		System.out.println("Connected");

		for ( int i = 1 ; i <= 500 ; i++ ) {
			
			String msg = "?" ;
			String topic = "jug/nantes" ;
			if ( i % 2 == 0 ) {
				msg = "Hello " + i ;
				topic = "jug/nantes/txt" ;
			}
			else {
				msg = "" + i  ;
				topic = "jug/nantes/num" ;
			}
			
			// Publish 
			System.out.println("Publish ( topic '"+ topic + "' ) : " + msg);
			MqttMessage mqttMessage = new MqttMessage(msg.getBytes());
			// Message options
			mqttMessage.setQos(QOS);
			client.publish(topic, mqttMessage );
			
			// Wait
			Thread.sleep(1000);
		}

		client.disconnect();
		System.out.println("Disconnected");
		
		client.close(); // Arret des threads
	}
}
