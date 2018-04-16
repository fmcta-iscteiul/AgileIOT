package Tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Launcher.Launch;
import Threads.CountryThread;
import Threads.RouletteThread;
import Threads.ThermoThread;


class ThreadTester {

	CountryThread countries;
	RouletteThread roulette;
	ThermoThread thermo;
	

	
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
	@Test
	void ThermoTest() {
		
		Launch.main(null);	
		
		thermo = new ThermoThread();
		assertNotNull(thermo);
		
		thermo.start();
		assertTrue(thermo.isAlive());
		
	}
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
