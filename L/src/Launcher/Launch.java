package Launcher;
import org.eclipse.paho.client.mqttv3.MqttException;

import GUI.GUI;

public class Launch {


	public static void main(String[] args) {

		GUI t = new GUI();
		try {
			t.workCycle();
		} catch (MqttException e1) {
			e1.printStackTrace();
		}

	}
}
