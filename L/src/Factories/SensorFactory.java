package Factories;

import javax.swing.JOptionPane;

import org.omg.DynamicAny.DynAnyPackage.InvalidValue;

import Threads.CountryThread;
import Threads.RouletteThread;
import Threads.ThermoThread;


/**
 * 
 * This class serves as a logical factory for the creation of the sensor threads.
 * Not only creates but also keeps track of the number of threads on the runtime
 * 
 * @author Group B
 * @version 1.0
 */

public class SensorFactory {

	

	public static final int MAX_SENSORS = 7;
	public static int SensorNum =0;

	/**
	 * Enum adressing the different types of sensors
	 *
	 */
	
	public enum sensorType {
		THERMO,
		COUNTRIES,
		ROULETTE
	}

	/**
	 * Creates the sensors until maximum number is achieved
	 * @param type type of sensor to be created
	 * @throws InvalidValue if reached maximum number of active sensors
	 */
	
	public static void makeSensor(sensorType type) throws InvalidValue {
		if (SensorNum==MAX_SENSORS) {
			throw new InvalidValue();
		}else {
			
			switch (type) {	
			case THERMO:
				SensorNum++;
				JOptionPane.showMessageDialog(null, "Messages on the topic: Agile_Thermo ");
				ThermoThread t = new ThermoThread();
				t.start();

				break;

			case COUNTRIES:
				SensorNum++;
				JOptionPane.showMessageDialog(null, "Messages on the topic: Agile_Countries ");
				CountryThread c = new CountryThread();
				c.start();

				break;

			case ROULETTE:
				SensorNum++;
				JOptionPane.showMessageDialog(null, "Messages on the topic: Agile_Roulette ");
				RouletteThread r = new RouletteThread();
				r.start();
				
				break;

			default:
				break;
			}
		}
	}
	
	/**
	 * Returns maximum number of active sensors in a runtime 
	 * @return maximum number of sensors
	 */
	public static int getMaxSensors() {
		return MAX_SENSORS;
	}

	/**
	 * Returns curent number of sensors created in runtime
	 * @return number of sensors created
	 */
	public static int getSensorNum() {
		return SensorNum;
	}

}
