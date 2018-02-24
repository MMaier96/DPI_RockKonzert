package application.data;

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

	public String getCombinednamespath1() {
		return combinedNamesPath1;
	}

	public String getCombinednamespath2() {
		return combinedNamesPath2;
	}

	public String getCombinednamespath3() {
		return combinedNamesPath3;
	}

	public String getForenamesPath() {
		return forenamesPath;
	}

	public String getSurnamesPath() {
		return surnamesPath;
	}

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

	public File getCombinedNamesFile1() {
		return combinedNamesFile1;
	}

	public File getCombinedNamesFile2() {
		return combinedNamesFile2;
	}

	public File getCombinedNamesFile3() {
		return combinedNamesFile3;
	}

	public ArrayList<String> getForenames() {
		return forenames;
	}

	public File getForenamesFile() {
		return forenamesFile;
	}

	public MersenneTwisterFast getRandomGemerator() {
		return randomGemerator;
	}

	public ArrayList<String> getSurnames() {
		return surnames;
	}

	public File getSurnamesFile() {
		return surnamesFile;
	}

	public ParticipantTicketGenerator getTicketGenerator() {
		return ticketGenerator;
	}

	public ArrayList<String> getTicketList() {
		return ticketList;
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

	private void readTickets() {
		ticketGenerator.create();
		ticketList = ticketGenerator.getTicketList();
	}

	public void setCombinedNamesFile1(File combinedNamesFile1) {
		this.combinedNamesFile1 = combinedNamesFile1;
	}

	public void setCombinedNamesFile2(File combinedNamesFile2) {
		this.combinedNamesFile2 = combinedNamesFile2;
	}

	public void setCombinedNamesFile3(File combinedNamesFile3) {
		this.combinedNamesFile3 = combinedNamesFile3;
	}

	public void setForenames(ArrayList<String> forenames) {
		this.forenames = forenames;
	}

	public void setForenamesFile(File forenamesFile) {
		this.forenamesFile = forenamesFile;
	}

	public void setRandomGemerator(MersenneTwisterFast randomGemerator) {
		this.randomGemerator = randomGemerator;
	}

	public void setSurnames(ArrayList<String> surnames) {
		this.surnames = surnames;
	}

	public void setSurnamesFile(File surnamesFile) {
		this.surnamesFile = surnamesFile;
	}

	public void setTicketGenerator(ParticipantTicketGenerator ticketGenerator) {
		this.ticketGenerator = ticketGenerator;
	}

	public void setTicketList(ArrayList<String> ticketList) {
		this.ticketList = ticketList;
	}

	public void start() {
		printInfo("Reading data from the csv files ... ");
		readData();

		printInfo("Creating tickets for the concert ... ");
		readTickets();

		printInfo("Saving combined names to 'combined_names.csv' ... ");
		exportData();
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

					builder.append(surnames.get(randomGemerator.nextInt(AppConfig.instance.amountParticipants)))
							.append(",")
							.append(forenames.get(randomGemerator.nextInt(AppConfig.instance.amountParticipants)))
							.append(",").append(ticket).append("\n");
					indoorCounter--;
				}
				continue;
			}

			if (ticket.charAt(0) > 65 && ticket.charAt(0) < 82) { // outdoor
				if (outdoorCounter > 0) {
					ticketList.remove(randomTicketNumber);

					builder.append(surnames.get(randomGemerator.nextInt(AppConfig.instance.amountParticipants)))
							.append(",")
							.append(forenames.get(randomGemerator.nextInt(AppConfig.instance.amountParticipants)))
							.append(",").append(ticket).append("\n");
					outdoorCounter--;
				}
				continue;
			}
		} while ((indoorCounter + outdoorCounter) > 0);

		output.write(builder.toString());
		output.close();
	}
}
