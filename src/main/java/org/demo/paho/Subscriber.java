package org.demo.paho;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Subscriber implements MqttCallback, AutoCloseable{
	
	private final String topicFilter ;
	private final String clientId ;
	private final IMqttClient client ;
	
	public Subscriber(String serverURI, String topicFilter) throws MqttException {
		super();
		this.topicFilter = topicFilter;
		this.clientId = UUID.randomUUID().toString() ;
		this.client = new MqttClient(serverURI, clientId);
		this.client.setCallback(this);
	}
	
	private MqttConnectOptions getOptions() {
		// Connect options
		MqttConnectOptions connectOptions = new MqttConnectOptions();
		connectOptions.setAutomaticReconnect(true);
		connectOptions.setCleanSession(true);
		connectOptions.setConnectionTimeout(10);
		// connectOptions.setUserName(userName);
		// connectOptions.setPassword(password);
		return connectOptions;
	}

	public boolean isConnected() {
		return client.isConnected(); 
	}

	public void subscribe() throws MqttException {
//			// Connect options
//			MqttConnectOptions connectOptions = new MqttConnectOptions();
//			connectOptions.setAutomaticReconnect(true);
//			connectOptions.setCleanSession(true);
//			connectOptions.setConnectionTimeout(10);
//			// connectOptions.setUserName(userName);
//			// connectOptions.setPassword(password);

//			// Client connection
//			IMqttClient client = new MqttClient("tcp://iot.eclipse.org:1883", clientId);
//			client.connect(connectOptions);
//			System.out.println("Connected.");
			
			// Check if connected 
			if ( ! client.isConnected() ) {
				// Connection 
				client.connect(getOptions());
			}
			
			// Subscribe 
			client.subscribe(topicFilter);
			
//			// Disconnect 
//			client.disconnect();
//			System.out.println("Disconnected.");
//
//			//-----------------------------------------------------------
//			// Close 
//			//-----------------------------------------------------------
//			client.close(); // Cannot re-connect
			
	}
	
	@Override
	public void close() { 
		try {
			if ( client.isConnected() ) {
				client.disconnect();
			}
			client.close(); // Cannot re-connect
		} catch (MqttException e) {
			throw new RuntimeException(e);
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
