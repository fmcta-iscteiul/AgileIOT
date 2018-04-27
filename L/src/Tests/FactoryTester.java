package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.omg.DynamicAny.DynAnyPackage.InvalidValue;
import Factories.SensorFactory;
import Factories.SensorFactory.sensorType;

/**
 * This test class is run to acess the behavior of the factory used to create all sensor Threads
 * @author Group B
 *
 */
class FactoryTester {

	/**
	 * Creates a dummyFactory out of which it is tested the maximum  number of sensors in the beginning.
	 * It creates various sensors and accesses if the last sensor is out of bonds.
	 * @throws InvalidValue if maximum number of sensors is reached
	 */
	@Test
	void testFactory() throws InvalidValue {
		
		@SuppressWarnings("unused")
		SensorFactory dummyFactory = new SensorFactory(); 

		assertEquals(SensorFactory.getMaxSensors(),7);
		assertEquals(SensorFactory.getSensorNum(),0);

		SensorFactory.makeSensor(sensorType.COUNTRIES);
		SensorFactory.makeSensor(sensorType.ROULETTE);
		SensorFactory.makeSensor(sensorType.THERMO);
		
		SensorFactory.makeSensor(sensorType.COUNTRIES);
		SensorFactory.makeSensor(sensorType.ROULETTE);
		SensorFactory.makeSensor(sensorType.THERMO);
		
		SensorFactory.makeSensor(sensorType.THERMO);
		assertEquals(SensorFactory.getMaxSensors(),7);
		assertEquals(SensorFactory.getSensorNum(),7);
		
		assertThrows(InvalidValue.class, new Executable() {

			@Override
			public void execute() throws Throwable {
				SensorFactory.makeSensor(sensorType.THERMO);	
			}
		});

	}

}
