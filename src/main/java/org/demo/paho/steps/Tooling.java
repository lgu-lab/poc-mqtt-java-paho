package org.demo.paho.steps;

import java.io.IOException;

public class Tooling {
	
	public static void waitUserInput() {
		try {
			System.in.read();
			System.out.println("--- USER INPUT ---");			
		} catch (IOException e) {
			//If we can't read we'll just exit
		}
	}

}
