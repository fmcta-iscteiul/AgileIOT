package Threads;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import GUI.GUI;

public class CountryThread extends Thread {



	private static String [] countries;

	public CountryThread() {
		countries = new String[197];
		ReadFile();
	}

	
	
	@Override
	public void run() {
		while(!this.isInterrupted()) {
			Random r = new Random();
			int choice = r.nextInt(197);
			GUI.sensorPublish("Agile_Countries", countries[choice]);
		}
}

	private void ReadFile() {
		File countryFile = new File("C:/Users/Filipe/git/AgileIOT/L/Docs/Countries");
		int iteration=0;
		try {
			Scanner scan = new Scanner(countryFile);
			while(scan.hasNextLine()) {
				countries[iteration]= scan.nextLine();
				iteration++;
			}
			scan.close();
			System.out.println(countries.length);
		} catch (FileNotFoundException e) {
		}

	}


	public String[] getCountries() {
		return countries;
	}


}
