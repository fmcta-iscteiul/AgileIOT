package Tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Launcher.Launch;
import Threads.CountryThread;
import Threads.RouletteThread;
import Threads.ThermoThread;

/**
 * Tester of the Threads representing the sensors 
 * @author Group B
 *
 */

class ThreadTester {

	CountryThread countries;
	RouletteThread roulette;
	ThermoThread thermo;
	

	/**
	 * Creates a country sensor and addresses if it's not NULL upon creation and if, after reading it's file, has the same amount of lines than the number of countries in the file.
	 */
	
	@Test
	void CountriesTest() {
		Launch.main(null);	
		countries = new CountryThread();
		assertNotNull(countries);
		assertNotNull(countries.getCountries());
		assertEquals(countries.getCountries().length,197);

		countries.start();
		assertTrue(countries.isAlive());
		
		countries.interrupt();
		assertTrue(countries.isInterrupted());
		
	}

	/**
	 * Creates termo sensor and tests whether it's running.
	 */
	
	@Test
	void ThermoTest() {
		
		Launch.main(null);	
		
		thermo = new ThermoThread();
		assertNotNull(thermo);
		
		thermo.start();
		assertTrue(thermo.isAlive());
		
	}
	/**
	 * Creates a roulette thread and checks if the roulette hashmap has 37 numbers
	 */
	@Test
	void RouletteTest() {
		
		Launch.main(null);	
		
		roulette = new RouletteThread();
		assertNotNull(roulette);
		assertNotNull(roulette.getRoulette());
		assertEquals(roulette.getRoulette().size(),37);
		
		roulette.start();
		assertTrue(roulette.isAlive());
		
	}
	
}
