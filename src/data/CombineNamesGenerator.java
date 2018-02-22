package data;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import application.config.AppConfig;
import application.random.MersenneTwisterFast;

import static application.logger.Logger.*;

public class CombineNamesGenerator {

	private static final String forenamesPath = "data/forenames.csv";
	private static final String surnamesPath = "data/surnames.csv";
	private static final String combinedNamesPath = "data/combined_names.csv";

	private File forenamesFile;
	private File surnamesFile;
	private File combinedNamesFile;

	private ArrayList<String> forenames;
	private ArrayList<String> surnames;

	private MersenneTwisterFast randomGemerator;

	public CombineNamesGenerator() {
		printMessage("CombineNamesGenerator started!");
		printInfo("setting up CombineNamesGenerator ... ");
		
		this.forenamesFile = new File(forenamesPath);
		this.surnamesFile = new File(surnamesPath);
		this.combinedNamesFile = new File(combinedNamesPath);

		forenames = new ArrayList<String>();
		surnames = new ArrayList<String>();

		randomGemerator = new MersenneTwisterFast();
	}

	private void exportData() {
		PrintWriter output = null;

		try {
			output = new PrintWriter(combinedNamesFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < AppConfig.instance.amountParticipants; i++) {
			builder.append(surnames.get(randomGemerator.nextInt(AppConfig.instance.amountParticipants))).append(",")
					.append(forenames.get(randomGemerator.nextInt(AppConfig.instance.amountParticipants))).append("\n");
		}

		output.write(builder.toString());
		output.close();
	}

	private void readData() {
		Scanner inputForenames = null;
		Scanner inputSurnames = null;

		try {
			inputForenames = new Scanner(forenamesFile);
			inputSurnames = new Scanner(surnamesFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (inputForenames.hasNextLine()) {
			String actualLine = inputForenames.nextLine();
			actualLine = actualLine.substring(0,1).toUpperCase() + actualLine.substring(1).toLowerCase();
			forenames.add(actualLine);
		}

		while (inputSurnames.hasNextLine()) {
			String actualLine = inputSurnames.nextLine();
			actualLine = actualLine.substring(0,1).toUpperCase() + actualLine.substring(1).toLowerCase();
			surnames.add(actualLine);
		}

		inputForenames.close();
		inputSurnames.close();
	}

	public void start() {
		printInfo("reading data from the csv files ... ");
		readData();
		printInfo("saving combined names to 'combined_names.csv' ... ");
		exportData();
	}
}
