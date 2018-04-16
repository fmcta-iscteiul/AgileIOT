package Threads;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import GUI.GUI;

public class RouletteThread extends Thread {

	private static HashMap<String, String > roulette = new HashMap<String,String>(); 


	public RouletteThread() {
		ReadFile();	
	}
	
	@Override
	public void run() {
		while(!this.isInterrupted()) {
		Random r = new Random();
		int choice = r.nextInt(37);
		GUI.sensorPublish("Agile_Roulette","Your number is: "+choice+" ["+roulette.get(""+choice)+"]" );
		}
}

	private void ReadFile() {
		
		File roulette_file = new File("C:/Users/Filipe/git/AgileIOT/L/Docs/Roulette");	
		try {
			Scanner scan = new Scanner(roulette_file);
			
			while(scan.hasNextLine()){
				String[] token = scan.nextLine().split(" ");
				roulette.put(token[0], token[1]);
			}
			scan.close();
		} catch (FileNotFoundException e) {
		}		
	}
	
	public HashMap<String, String> getRoulette() {
		return roulette;
	}
}
