package org.demo.paho;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class HelloPahoSubscriber implements MqttCallback {
	
	private final String topicFilter ;
	private final String clientId ;
	
	public HelloPahoSubscriber(String topicFilter) {
		super();
		this.topicFilter = topicFilter;
		this.clientId = UUID.randomUUID().toString() ;
		System.out.println("Subscriber created");
	}

	public void subscribe() {
		try {
			
			// Connect options
			MqttConnectOptions connectOptions = new MqttConnectOptions();
			connectOptions.setAutomaticReconnect(true);
			connectOptions.setCleanSession(true);
			connectOptions.setConnectionTimeout(10);
			// connectOptions.setUserName(userName);
			// connectOptions.setPassword(password);

			// Client connection
			IMqttClient client = new MqttClient("tcp://iot.eclipse.org:1883", clientId);
			client.connect(connectOptions);
			System.out.println("Connected.");
			
			// Subscribe 
			if ( client.isConnected() ) {
				client.setCallback(this);
				client.subscribe(topicFilter);
				System.out.println("Subscribe OK, waiting for messages...");
			}
			else {
				throw new RuntimeException("MQTT client is not connected");
			}
			
			// Disconnect 
			client.disconnect();
			System.out.println("Disconnected.");

			//-----------------------------------------------------------
			// Close 
			//-----------------------------------------------------------
			client.close(); // Cannot re-connect
			
			
		} catch (MqttException e) {
			System.out.println("MQTT ERROR");
			e.printStackTrace();
		}

	}

	@Override
	public void connectionLost(Throwable cause) {
		System.out.println("Connection Lost !");
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println("Message arrived : " + message );
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("Delivery complete : " + token);
	}

}
