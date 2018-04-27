package Threads;

import java.util.Random;

import GUI.GUI;

/**
 * This Thread represents a thermometer 
 * @author Group B
 *
 */
public class ThermoThread extends Thread {

/**
 * Sends the broker random temperature readings
 */

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
