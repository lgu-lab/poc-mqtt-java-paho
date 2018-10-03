package org.demo.paho;

public class RunSubscriber {
	

	public static void main(String[] args) {
		
		final String TOPIC = "/a/b/c";
		
		HelloPahoSubscriber subscriber = new HelloPahoSubscriber(TOPIC);
		subscriber.subscribe();
	}
}
