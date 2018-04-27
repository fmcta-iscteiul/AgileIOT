package Launcher;
import org.eclipse.paho.client.mqttv3.MqttException;

import GUI.GUI;

/**
 * This class serves as a facade for the sensor simulator
 * @author Group B
 *
 */
public class Launch {


	/**
	 * Creates a sensor simulator and starts it's runtime
	 * @param args
	 */
	public static void main(String[] args) {
		GUI t = new GUI();
		try {
			t.workCycle();
		} catch (MqttException e1) {
			e1.printStackTrace();
		}

	}
}
