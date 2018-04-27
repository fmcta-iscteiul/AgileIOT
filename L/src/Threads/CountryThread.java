package Threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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


	public String[] getCountries() {
		return countries;
	}


}
