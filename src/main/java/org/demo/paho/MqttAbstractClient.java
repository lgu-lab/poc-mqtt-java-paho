package org.demo.paho;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public abstract class MqttAbstractClient implements AutoCloseable {

	protected final static int      DEFAULT_QOS = 0;
	protected final static boolean  DEFAULT_RETAINED = false ;
	
//	private final String serverURI ;
	private final String clientId ;
	private final IMqttClient client ;
	private final MqttConnectOptions options ;
	
	public MqttAbstractClient(String serverURI) throws MqttException {
		super();
//		this.serverURI = serverURI;
		this.clientId = UUID.randomUUID().toString() ;
		this.client = new MqttClient(serverURI, clientId);
		this.options = getDefaultOptions();
	}

	private MqttConnectOptions getDefaultOptions() {
		// Connect options
		MqttConnectOptions connectOptions = new MqttConnectOptions();
		connectOptions.setAutomaticReconnect(true);
		connectOptions.setCleanSession(true);
		connectOptions.setConnectionTimeout(10);
		// connectOptions.setUserName(userName);
		// connectOptions.setPassword(password);
		return connectOptions;
	}

	protected void checkConnected() throws MqttException {
		if ( ! isConnected() ) {
			// Connection 
			client.connect(options);
		}
	}

	protected IMqttClient getMqttClient() {
		return client ;
	}
	
	public MqttConnectOptions getOptions() {
		return options;
	}
	
	public boolean isConnected() {
		return client.isConnected(); 
	}

	@Override
	public void close() { 
		try {
			if ( client.isConnected() ) {
				client.disconnect();
			}
			client.close(); // Cannot re-connect after close
		} catch (MqttException e) {
			throw new RuntimeException(e);
		}
	}
	
}
