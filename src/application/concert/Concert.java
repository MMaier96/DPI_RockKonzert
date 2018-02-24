package application.concert;

import static application.logger.Logger.printInfo;
import static application.logger.Logger.printMessage;
import static application.logger.Logger.printSeperator;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import application.architecture.ILocation;
import application.architecture.Stadion;
import application.architecture.areas.IArea;
import application.architecture.areas.IWaitingArea;
import application.architecture.sectors.ISector;
import application.architecture.sectors.Seat;
import application.config.AppConfig;
import application.persons.ConcertMediator;
import application.persons.IConcertMediator;
import application.persons.Participant;
import application.phases.IPhase;
import application.phases.Phase1;
import application.phases.Phase2;
import application.phases.Phase3;
import application.random.MersenneTwisterFast;
import application.tickets.Ticket;

public class Concert {

	private IPhase actualPhase;
	private IConcertMediator eventManager;
	private ILocation location;
	private ArrayList<Participant> participantsPhase1;
	private ArrayList<Participant> participantsPhase2;
	private ArrayList<Participant> participantsPhase3;
	private ArrayList<IPhase> phases;

	public Concert() {
		printMessage("Concert creation started!");
		printInfo("Building the concert");
		participantsPhase1 = new ArrayList<Participant>();
		participantsPhase2 = new ArrayList<Participant>();
		participantsPhase3 = new ArrayList<Participant>();

		phases = new ArrayList<IPhase>() {
			private static final long serialVersionUID = 6632574496661821577L;
			{
				add(new Phase1());
				add(new Phase2());
				add(new Phase3());
			}
		};

		printInfo(phases.size() + " phases were created! " + phases.toString());
	}

	public void addParticipant(Participant participant) {
		participantsPhase1.add(participant);
	}

	private void close() {
		ArrayList<Seat> allEmptySeats = new ArrayList<Seat>();
		ArrayList<IArea> indoorAreas = location.getIndoorAreas();
		ArrayList<IArea> outdoorAreas = location.getOutdoorAreas();
		for (IArea iArea : indoorAreas) {
			for (int i = 0; i < AppConfig.instance.amountIndoorSectorsPerArea; i++) {
				ISector sectorByIndex = iArea.getSectorByIndex(i);
				allEmptySeats.addAll(sectorByIndex.getEmptySeats());
			}
		}

		for (IArea iArea : outdoorAreas) {
			for (int i = 0; i < AppConfig.instance.amountOutdoorSectorsPerArea; i++) {
				ISector sectorByIndex = iArea.getSectorByIndex(i);
				allEmptySeats.addAll(sectorByIndex.getEmptySeats());
			}
		}
		printInfo("All empty seats after 3 phases: " + allEmptySeats);
		printMessage("The concert is finished!");
		System.exit(0);
	}

	private void createTickets() {
		for (Participant participant : participantsPhase1) {
			IArea areaByName = location.getAreaByName(participant.getAreaName());
			ISector sector = areaByName.getSectorByIndex(participant.getSectorId() - 1);
			Seat seat = sector.getSeatBySeatNumber(participant.getSeatNumber());

			participant.setTicket(new Ticket(areaByName, sector, seat));
		}

		for (Participant participant : participantsPhase2) {
			IArea areaByName = location.getAreaByName(participant.getAreaName());
			ISector sector = areaByName.getSectorByIndex(participant.getSectorId() - 1);
			Seat seat = sector.getSeatBySeatNumber(participant.getSeatNumber());

			participant.setTicket(new Ticket(areaByName, sector, seat));
		}

		for (Participant participant : participantsPhase3) {
			IArea areaByName = location.getAreaByName(participant.getAreaName());
			ISector sector = areaByName.getSectorByIndex(participant.getSectorId() - 1);
			Seat seat = sector.getSeatBySeatNumber(participant.getSeatNumber());

			participant.setTicket(new Ticket(areaByName, sector, seat));
		}
	}

	private void fillWaitingAreas() {
		MersenneTwisterFast randomGenerator = new MersenneTwisterFast();
		int phaseId = actualPhase.getPhaseId();
		ArrayList<Participant> tempParticipant = null;

		switch (phaseId) {
		case 1:
			tempParticipant = participantsPhase1;
			break;

		case 2:
			tempParticipant = participantsPhase2;
			break;

		case 3:
			tempParticipant = participantsPhase3;
			break;

		default:
			break;
		}

		int randomParticipantIndex = 0;
		IWaitingArea waitingAreaByIndex = null;

		int prevSize = tempParticipant.size();
		for (int i = 0; i < prevSize; i++) {
			randomParticipantIndex = randomGenerator.nextInt(tempParticipant.size());
			waitingAreaByIndex = location.getWaitingAreaByIndex(randomGenerator.nextInt(10));

			waitingAreaByIndex.addWaitingParticipant(tempParticipant.get(randomParticipantIndex));
			tempParticipant.remove(randomParticipantIndex);
		}
	}

	public IPhase getActualPhase() {
		return actualPhase;
	}

	public IConcertMediator getEventManager() {
		return eventManager;
	}

	public ILocation getLocation() {
		return location;
	}

	public ArrayList<Participant> getParticipants() {
		return participantsPhase1;
	}

	private void loadParticipants1() {
		File namesFile = null;
		Scanner inputNames = null;

		try {
			namesFile = new File("data/participants_tickets_1.csv");
			inputNames = new Scanner(namesFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (inputNames.hasNextLine()) {
			String actualLine = inputNames.nextLine();

			String forenname = actualLine.split(",")[0];
			String surname = actualLine.split(",")[1];
			String areaName = actualLine.split(",")[2];
			String sectorId = actualLine.split(",")[3];
			String seatNumber = actualLine.split(",")[4];

			participantsPhase1.add(new Participant(forenname, surname, areaName, sectorId, seatNumber));
		}

		inputNames.close();
	}

	private void loadParticipants2() {
		File namesFile = null;
		Scanner inputNames = null;

		try {
			namesFile = new File("data/participants_tickets_2.csv");
			inputNames = new Scanner(namesFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (inputNames.hasNextLine()) {
			String actualLine = inputNames.nextLine();

			String forenname = actualLine.split(",")[0];
			String surname = actualLine.split(",")[1];
			String areaName = actualLine.split(",")[2];
			String sectorId = actualLine.split(",")[3];
			String seatNumber = actualLine.split(",")[4];

			participantsPhase2.add(new Participant(forenname, surname, areaName, sectorId, seatNumber));
		}

		inputNames.close();
	}

	private void loadParticipants3() {
		File namesFile = null;
		Scanner inputNames = null;

		try {
			namesFile = new File("data/participants_tickets_3.csv");
			inputNames = new Scanner(namesFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (inputNames.hasNextLine()) {
			String actualLine = inputNames.nextLine();

			String forenname = actualLine.split(",")[0];
			String surname = actualLine.split(",")[1];
			String areaName = actualLine.split(",")[2];
			String sectorId = actualLine.split(",")[3];
			String seatNumber = actualLine.split(",")[4];

			participantsPhase3.add(new Participant(forenname, surname, areaName, sectorId, seatNumber));
		}

		inputNames.close();
	}

	public void nextPhase() {
		location.resetWaitingAreas();

		int indexAcutalPhase = phases.indexOf(actualPhase);
		if ((indexAcutalPhase + 1) >= phases.size()) {
			close();
		} else if (indexAcutalPhase == -1) {
			actualPhase = phases.get(0);
			printMessage("Next phase starts ...");
			printInfo("The current phase is: " + actualPhase.toString());
		} else {
			printMessage("Next phase starts ...");
			actualPhase = phases.get(indexAcutalPhase + 1);
			printInfo("The current phase is: " + actualPhase.toString());
		}

		fillWaitingAreas();

		printInfo("All participants are in the waiting areas");

		printInfo("Participants in the car waiting areas: " + location.getCarWaitingAreasSize());
		printInfo("Participants in the public transportation waiting areas: "
				+ location.getPublicTransportationWaitingAreasSize());
	}

	public void removeParticipant(Participant participant) {
		if (participantsPhase1.contains(participant)) {
			participantsPhase1.remove(participant);
		}
	}

	public void setActualPhase(IPhase actualPhase) {
		this.actualPhase = actualPhase;
	}

	public void setEventManager(IConcertMediator eventManager) {
		this.eventManager = eventManager;
	}

	public void setLocation(ILocation location) {
		this.location = location;
	}

	public void start() {
		printInfo("Loading the participants for phase 1...");
		loadParticipants1();
		printInfo(participantsPhase1.size() + " participants were loaded!");

		printInfo("Loading the participants for phase 2...");
		loadParticipants2();
		printInfo(participantsPhase2.size() + " participants were loaded!");

		printInfo("Loading the participants for phase 3...");
		loadParticipants3();
		printInfo(participantsPhase3.size() + " participants were loaded!");
		printInfo((participantsPhase1.size() + participantsPhase2.size() + participantsPhase3.size())
				+ " total participants were loaded!");

		this.location = new Stadion();
		printSeperator();
		printInfo("Creating tickets ... ");
		createTickets();
		printInfo("Tickets were created for every participant!");
		printInfo("A stadion was set as the concerts location!");

		this.eventManager = new ConcertMediator(this);
		location.setEventManager(eventManager);
		location.registerDisplays();
		eventManager.startManaging();

	}
}
