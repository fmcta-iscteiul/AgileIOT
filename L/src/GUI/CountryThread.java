package GUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class CountryThread extends Thread {

	private String [] countries = new String[197];

	public CountryThread() {
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
		File countryFile = new File("./Docs/Countries");
		int iteration=0;
		try {
			Scanner scan = new Scanner(countryFile);
			while(scan.hasNextLine()) {
				countries[iteration]= scan.nextLine();
				iteration++;
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}


}
