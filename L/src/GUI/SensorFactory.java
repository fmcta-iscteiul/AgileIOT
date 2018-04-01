package GUI;

import javax.swing.JOptionPane;

import org.omg.DynamicAny.DynAnyPackage.InvalidValue;

public class SensorFactory {

	public static final int MAX_SENSORS = 3;
	public static int SensorNum =0;

	public enum sensorType {
		THERMO,
		COUNTRIES,
		ROULETTE
	}

	public SensorFactory() {

	}

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
				//

				break;

			case COUNTRIES:
				SensorNum++;
				JOptionPane.showMessageDialog(null, "Messages on the topic: Agile_Countries ");
				CountryThread c = new CountryThread();
				c.start();
				//

				break;

			case ROULETTE:
				SensorNum++;
				JOptionPane.showMessageDialog(null, "Messages on the topic: Agile_Roulette ");
				RouletteThread r = new RouletteThread();
				r.start();
				//

				break;

			default:
				break;
			}
		}
	}

}
