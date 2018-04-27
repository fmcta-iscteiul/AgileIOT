package Threads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import GUI.GUI;

/**
 * This class represents a main sensor of a net of sub-sensors which are allocated in every country of the world.
 * This sensor sends information about the names of the countries in which a sensor just deployed some information. 
 * @author Group B
 *
 */

public class CountryThread extends Thread {

	private static String [] countries;
	
	
	/**
	 * Creates a vector of the number of all the countries in the world
	 * and fills it with the names of all of them.
	 */
	public CountryThread() {
		countries = new String[197];
		ReadFile();
	}



/**
 * Publishes a random country
 */
	@Override
	public void run() {
		while(!this.isInterrupted()) {
			Random r = new Random();
			int choice = r.nextInt(197);
			GUI.sensorPublish("Agile_Countries", countries[choice]);
		}
	}


	/**
	 * Acesses the specific file containing all the countries in the world and reads from there to the vector.
	 */
	private void ReadFile() {

		InputStream in = getClass().getResourceAsStream("/Threads/Countries");	
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		int iteration=0;

		String currentCountry;
		try {
			while((currentCountry =reader.readLine())!= null) {
				countries[iteration]=currentCountry;
				iteration++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	/**
	 * Returns the vector containing all the world's countries' names
	 * @return world's countries' names
	 */
	public String[] getCountries() {
		return countries;
	}


}
