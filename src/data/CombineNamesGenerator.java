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
	private static final String combinedNamesPath1 = "data/participants_tickets_1.csv";
	private static final String combinedNamesPath2 = "data/participants_tickets_2.csv";
	private static final String combinedNamesPath3 = "data/participants_tickets_3.csv";

	private File forenamesFile;
	private File surnamesFile;
	private File combinedNamesFile1;
	private File combinedNamesFile2;
	private File combinedNamesFile3;

	private ArrayList<String> forenames;
	private ArrayList<String> surnames;
	private ArrayList<String> ticketList;

	private MersenneTwisterFast randomGemerator;
	ParticipantTicketGenerator ticketGenerator;

	public CombineNamesGenerator() {
		printMessage("CombineNamesGenerator started!");
		printInfo("Setting up CombineNamesGenerator ... ");

		this.forenamesFile = new File(forenamesPath);
		this.surnamesFile = new File(surnamesPath);
		this.combinedNamesFile1 = new File(combinedNamesPath1);
		this.combinedNamesFile2 = new File(combinedNamesPath2);
		this.combinedNamesFile3 = new File(combinedNamesPath3);

		forenames = new ArrayList<String>();
		surnames = new ArrayList<String>();

		randomGemerator = new MersenneTwisterFast();
		ticketGenerator = new ParticipantTicketGenerator();
	}

	private void exportData() {
		writeToFile(combinedNamesFile1, 0.2, 0.5);
		writeToFile(combinedNamesFile2, 0.5, 0.3);
		writeToFile(combinedNamesFile3, 0.3, 0.2);
	}

	private void writeToFile(File file, double indoorCapacity, double outdoorCapacity) {
		PrintWriter output = null;
		int indoorCounter = (int) ((double) AppConfig.instance.indoorSeats * indoorCapacity);
		int outdoorCounter = (int) ((double) AppConfig.instance.outdoorSeats * outdoorCapacity);
		StringBuilder builder = new StringBuilder();
		String ticket = "";

		try {
			output = new PrintWriter(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int randomTicketNumber = 0;

		// indoor
		do {
			randomTicketNumber = randomGemerator.nextInt(ticketList.size());
			ticket = ticketList.get(randomTicketNumber);
			if (ticket.charAt(0) == 65) { // indoor
				if (indoorCounter > 0) {
					ticketList.remove(randomTicketNumber);

					builder.append(surnames.get(randomGemerator.nextInt(AppConfig.instance.amountParticipants))).append(",")
							.append(forenames.get(randomGemerator.nextInt(AppConfig.instance.amountParticipants))).append(",")
							.append(ticket).append("\n");
					indoorCounter--;
				}
				continue;
			}

			if (ticket.charAt(0) > 65 && ticket.charAt(0) < 82) { // outdoor
				if (outdoorCounter > 0) {
					ticketList.remove(randomTicketNumber);

					builder.append(surnames.get(randomGemerator.nextInt(AppConfig.instance.amountParticipants))).append(",")
							.append(forenames.get(randomGemerator.nextInt(AppConfig.instance.amountParticipants))).append(",")
							.append(ticket).append("\n");
					outdoorCounter--;
				}
				continue;
			}
		} while ((indoorCounter + outdoorCounter) > 0);

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
			actualLine = actualLine.substring(0, 1).toUpperCase() + actualLine.substring(1).toLowerCase();
			forenames.add(actualLine);
		}

		while (inputSurnames.hasNextLine()) {
			String actualLine = inputSurnames.nextLine();
			actualLine = actualLine.substring(0, 1).toUpperCase() + actualLine.substring(1).toLowerCase();
			surnames.add(actualLine);
		}

		inputForenames.close();
		inputSurnames.close();
	}

	public void start() {
		printInfo("Reading data from the csv files ... ");
		readData();

		printInfo("Creating tickets for the concert ... ");
		readTickets();

		printInfo("Saving combined names to 'combined_names.csv' ... ");
		exportData();
	}

	private void readTickets() {
		ticketGenerator.create();
		ticketList = ticketGenerator.getTicketList();
	}
}
