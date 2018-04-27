package Threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import GUI.GUI;
/**
 * This class represents a sensor installed in a real roulette which is part of internet gamble game.
 * @author Group B
 *
 */
public class RouletteThread extends Thread {
	private static HashMap<String, String > roulette = new HashMap<String,String>(); 

	
/**
 * Creates the Thread and fills the hashmap with information
 */
	public RouletteThread() {
		ReadFile();	
	}


	/**
	 * Published to the broker the roulette information
	 */
	@Override
	public void run() {
		while(!this.isInterrupted()) {
			Random r = new Random();
			int choice = r.nextInt(37);
			GUI.sensorPublish("Agile_Roulette","Your number is: "+choice+" ["+roulette.get(""+choice)+"]" );
		}
	}

	/**
	 * Reads from selected file and maps the number to it's color
	 */
	private void ReadFile() {
		InputStream in = getClass().getResourceAsStream("/Threads/Roulette");	
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		String currentNumber;
		try {
			while((currentNumber =reader.readLine())!= null) {
				String[] token = currentNumber.split(" ");
				roulette.put(token[0], token[1]);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


/**
 * Returns the roulette hasmap
 * @return roulette map
 */
	public HashMap<String, String> getRoulette() {
		return roulette;
	}
}
