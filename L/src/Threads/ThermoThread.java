package Threads;

import java.util.Random;

import GUI.GUI;

public class ThermoThread extends Thread {

	
	public ThermoThread() {
	}
	
	@Override
	public void run() {
		while(!this.isInterrupted()) {
		Random r = new Random();
		int degree = r.nextInt(51);
		String contentParsed =""+degree;
			GUI.sensorPublish("Agile_Thermo", contentParsed);
		
		}
		

	}
}
