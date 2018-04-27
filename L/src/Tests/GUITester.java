package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.Test;

import GUI.GUI;
import Launcher.Launch;

/**
 * Tester class to test the GUI, the connections, the publishing, the connection options and the functionality of each button.
 * @author Group B
 *
 */
class GUITester {

	/**
	 * Test of the attributes of the connection
	 */
	@Test
	void testAttributes() {
		GUI gui = new GUI();
		assertEquals(GUI.getTopic(),"MQTT Examples");
		assertEquals(GUI.getQos(),2);
		assertEquals(GUI.getBroker(),"tcp://iot.eclipse.org:1883");
		assertEquals(gui.getClientId(),"JavaSample");
	}

	/**
	 * Test the different states of the connection
	 */
	@Test
	void testConnection() {		
		GUI gui = new GUI();
		gui.doCreate();
		gui.doConnection();
		assertTrue(GUI.getSampleClient().isConnected());

		gui.doDisconnect();
		assertFalse(GUI.getSampleClient().isConnected());

	}

	/**
	 * Test connection options
	 */
	@Test
	void testConnectionOptions() {
		@SuppressWarnings("unused")
		Launch l = new Launch();
		GUI gui = new GUI();
		gui.doCreate();
		assertTrue(gui.getConnOpts().isCleanSession());
		gui.doPublish();
	}

	/**
	 * Test functionality of each button
	 */
	@Test
	void testButtons() {
		GUI gui = new GUI();
		try {
			gui.workCycle();
		} catch (MqttException e) {
		}
		for (int i = 0; i < gui.getButtons().size(); i++) {
			gui.getButtons().get(i).doClick();
		}
	}
}
