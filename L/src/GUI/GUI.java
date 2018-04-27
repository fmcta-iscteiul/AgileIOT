package GUI;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.omg.DynamicAny.DynAnyPackage.InvalidValue;

import Factories.SensorFactory;
import Factories.SensorFactory.sensorType;

/**
 * This class creates and manages the GUI for the manual and automated sensors.
 * It is responsible for the creation of the connection between the sensor and the broker, for the publishing of the messages
 * and for the disconnection of sessions.
 * 
 * @author Group B
 * @version 1.0
 */


public class GUI{

	protected static String topic        = "MQTT Examples";
	protected static String content               ="";
	protected static final int qos             = 2;
	protected static final String broker       = "tcp://iot.eclipse.org:1883";
	protected final String clientId     = "JavaSample";
	protected MemoryPersistence persistence = new MemoryPersistence();

	public static MqttClient sampleClient;
	protected MqttConnectOptions connOpts;

	protected static ArrayList<JButton> buttons = new ArrayList<>();


	/**
	 * This method creates the sensors, the connection, the GUI and all triggers for the sensors.
	 * 
	 * @throws MqttException
	 */
	public void workCycle() throws MqttException {
		doCreate();
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				@Override
				public void run() {
					JFrame frame = new JFrame("IOT Sensor Simulator");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setSize(700, 400);

					frame.setLayout(new GridLayout(2, 0));
					///////////////////////////////////////////////////AUTO////////////////////////////////

					//Upper panel
					JPanel upperPanel = new JPanel();
					upperPanel.setLayout(new BorderLayout());

					//AutoPanel
					JPanel autoPanel = new JPanel();
					autoPanel.setLayout(new GridLayout(1, 3));


					//Thermometer sensor
					JButton thermoButton = new JButton("Thermometer sensor");
					buttons.add(thermoButton);
					thermoButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {

							try {
								SensorFactory.makeSensor(sensorType.THERMO);
							} catch (InvalidValue e1) {
								JOptionPane.showMessageDialog(null, "There are already "+ SensorFactory.MAX_SENSORS +" sensors running");
							}

						}
					});
					autoPanel.add(thermoButton);

					//Country sensor
					JButton countryButton = new JButton("Country");
					buttons.add(countryButton);
					countryButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {	
							try {
								SensorFactory.makeSensor(sensorType.COUNTRIES);
							} catch (InvalidValue e1) {
								JOptionPane.showMessageDialog(null, "There are already "+ SensorFactory.MAX_SENSORS +" sensors running");
								e1.printStackTrace();
							}

						}
					});
					autoPanel.add(countryButton);

					//Roulette sensor
					JButton rouletteButton = new JButton("Roulette");
					buttons.add(rouletteButton);
					rouletteButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								SensorFactory.makeSensor(sensorType.ROULETTE);
							} catch (InvalidValue e1) {
								JOptionPane.showMessageDialog(null, "There are already "+ SensorFactory.MAX_SENSORS +" sensors running");
								e1.printStackTrace();
							}

						}
					});
					autoPanel.add(rouletteButton);

					//Adding automatic panel

					JLabel autoLabel = new JLabel("Sensor Modules");
					autoLabel.setHorizontalAlignment(JLabel.CENTER);

					upperPanel.add(autoLabel,BorderLayout.NORTH);

					upperPanel.add(autoPanel,BorderLayout.CENTER);		

					frame.add(upperPanel);



					///////////////////////////////////////////////////MANUAL//////////////////////////////	

					//UpperPane
					JPanel lowerPanel = new JPanel();
					lowerPanel.setLayout(new BorderLayout());


					//Manual Panel
					JPanel manualPanel = new JPanel();
					manualPanel.setLayout(new BorderLayout());


					//Topic Panel
					JPanel topicPanel = new JPanel();
					topicPanel.setLayout(new GridLayout(2, 1));
					JLabel topicLabel = new JLabel("Topic: ");
					JTextField topicText = new JTextField();
					topicPanel.add(topicLabel);
					topicPanel.add(topicText);
					manualPanel.add(topicPanel, BorderLayout.NORTH);

					//Message Panel
					JPanel messagePanel = new JPanel();
					messagePanel.setLayout(new BorderLayout());
					JLabel messageLabel = new JLabel("Message: ");
					JTextPane messageText = new JTextPane();
					JScrollPane scrollPane = new JScrollPane(messageText);
					messagePanel.add(messageLabel,BorderLayout.NORTH);
					messagePanel.add(scrollPane,BorderLayout.CENTER);
					manualPanel.add(messagePanel, BorderLayout.CENTER);

					//Send Button
					JPanel sendPanel = new JPanel();
					sendPanel.setLayout(new GridLayout(1,3));
					JButton sendButton = new JButton(" Send ");
					buttons.add(sendButton);
					sendButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							if(topicText.getText().isEmpty()) {
								topic= "[no decision]";
							}else
								topic= topicText.getText();
							if(messageText.getText().isEmpty()) {
								content= "[nothing]";
							}else
								content = messageText.getText();
							doPublish();

						}
					});
					JButton disconnectButton = new JButton("Disconnect");
					buttons.add(disconnectButton);
					disconnectButton.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							doDisconnect();
							frame.dispose();
						}
					});

					sendPanel.add(disconnectButton);
					sendPanel.add(new JLabel());
					sendPanel.add(sendButton);
					manualPanel.add(sendPanel,BorderLayout.SOUTH);

					//Adding Manual pane
					JLabel manualLabel = new JLabel("Manual");
					manualLabel.setHorizontalAlignment(JLabel.CENTER);

					lowerPanel.add(manualLabel, BorderLayout.NORTH);

					lowerPanel.add(manualPanel, BorderLayout.CENTER);


					frame.add(lowerPanel);


					///////////////////////////////////////////////////MANUAL//////////////////////////////	


					//Visibility
					frame.setVisible(true);

				}
			});
		} catch (InvocationTargetException | InterruptedException e) {
			e.printStackTrace();
		}
		doConnection();
	}

	/**
	 * Creates Client and connection options
	 */
	public void doCreate() {
		try {
			sampleClient = new MqttClient(broker, clientId, persistence);
		} catch (MqttException e) {
			e.printStackTrace();
		}

		connOpts = new MqttConnectOptions();
		connOpts.setCleanSession(true);

		System.out.println("Connecting to broker: "+broker);
	}

	/**
	 * Creates connections between broker and sensors
	 */
	public void doConnection() {
		try {
			sampleClient.connect(connOpts);
		} catch (MqttException e) {
			e.printStackTrace();
		}
		System.out.println("Connected");
	}

	/**
	 * Publishes message to the broker with specified content and topic
	 */

	public void doPublish() {
		System.out.println("Publishing message: "+content +" on topic: "+ topic);
		MqttMessage message = new MqttMessage(content.getBytes());
		message.setQos(qos);
		try {
			sampleClient.publish(topic, message);		
		} catch (MqttException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Disconects sensros from broker
	 */
	public void doDisconnect() {

		System.out.println("Message published");
		try {
			sampleClient.disconnect();
		} catch (MqttException e) {
			e.printStackTrace();
		}
		System.out.println("Disconnected");

	}

	/**
	 * Static access to publishing a message in a certain topic
	 * 
	 * @param Stopic the topic to which send the message
	 * @param Scontent the content of the message to send
	 */
	public synchronized static void sensorPublish(String Stopic, String Scontent) {

		MqttMessage message = new MqttMessage(Scontent.getBytes());
		message.setQos(qos);
		try {
			sampleClient.publish(Stopic, message);
		} catch (MqttException e) {
			e.printStackTrace();
		}		
	}

	/////////////Getters/////////////////////

	/**
	 * Returns the topic
	 * @return selected topic
	 */
	public static String getTopic() {
		return topic;
	}

	/**
	 * Returns the desired Quality of Service
	 * @return quality of service
	 */
	public static int getQos() {
		return qos;
	}

	/**
	 * Returns the broker associated with current runtime
	 * @return
	 * broker connected
	 */
	public static String getBroker() {
		return broker;
	}


	/**
	 * Returns the ID of the connected sensor simulator
	 * @return ID of current session's sensor 
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * Returns the exact sensor simulator
	 * @return
	 *sensor simulator in runtime
	 */
	public static MqttClient getSampleClient() {
		return sampleClient;
	}

	/**
	 * Returns the connection options of current session
	 * @return connection options
	 */
	public MqttConnectOptions getConnOpts() {
		return connOpts;
	}

	/**
	 * Returns a list of all GUI buttons
	 * @return list of all GUI buttons
	 */
	public ArrayList<JButton> getButtons() {
		return buttons;
	}

}

