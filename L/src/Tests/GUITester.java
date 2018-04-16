package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.Test;

import GUI.GUI;
import Launcher.Launch;

class GUITester {

	@Test
	void testAttributes() {
		GUI gui = new GUI();
		assertEquals(GUI.getTopic(),"MQTT Examples");
		assertEquals(GUI.getQos(),2);
		assertEquals(GUI.getBroker(),"tcp://iot.eclipse.org:1883");
		assertEquals(gui.getClientId(),"JavaSample");
		}
	
	@Test
	void testConnection() {		
		GUI gui = new GUI();
		gui.doCreate();
		gui.doConnection();
		assertTrue(GUI.getSampleClient().isConnected());
		
		gui.doDisconnect();
		assertFalse(GUI.getSampleClient().isConnected());
		
	}
	
	@Test
	void testConnectionOptions() {
		Launch l = new Launch();
		GUI gui = new GUI();
		gui.doCreate();
		assertTrue(gui.getConnOpts().isCleanSession());
		gui.doPublish();
	}

	@Test
	void testButtons() {
		GUI gui = new GUI();
		try {
			gui.workCycle();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
		}
		for (int i = 0; i < gui.getButtons().size(); i++) {
			gui.getButtons().get(i).doClick();
		}
	}
}
