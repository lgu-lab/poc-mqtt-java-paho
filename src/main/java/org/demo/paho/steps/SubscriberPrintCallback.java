package org.demo.paho.steps;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SubscriberPrintCallback implements MqttCallback {
	

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
