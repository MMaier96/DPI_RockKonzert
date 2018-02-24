package application;

import static application.logger.Logger.printMessage;

import application.concert.Concert;
import data.CombineNamesGenerator;

public class Application {

	public static void main(String[] args) {
		
		printMessage("RockConcert Simulation started!");
		
		CombineNamesGenerator namesGenerator = new CombineNamesGenerator();
		namesGenerator.start();
		
		Concert concert = new Concert();
		concert.start();
		
	}

}
