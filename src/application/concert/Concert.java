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
import application.architecture.sectors.ISector;
import application.architecture.sectors.Seat;
import application.persons.ConcertMediator;
import application.persons.IConcertMediator;
import application.persons.Participant;
import application.phases.IPhase;
import application.phases.Phase1;
import application.phases.Phase2;
import application.phases.Phase3;
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
		
		printInfo( phases.size() + " phases were created! " + phases.toString());
		
		actualPhase = phases.get(0);
		
		printInfo("The current phase is: " + actualPhase.toString());
		
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
		printInfo((participantsPhase1.size() + participantsPhase2.size()+ participantsPhase3.size())  + " total participants were loaded!");
		
		this.eventManager = new ConcertMediator();
		printInfo("EventManager/Mediator was created!");
		this.location = new Stadion();
		printInfo("Creating tickets ... ");
		createTickets();
		printInfo("Tickets were created for every participant!");
		printSeperator();
		printInfo("A stadion was set as the concerts location!");
		
	}
	
	private void createTickets() {
		for (Participant participant : participantsPhase1) {
			IArea areaByName = location.getAreaByName(participant.getAreaName());
			ISector sector = areaByName.getSectorByIndex(participant.getSectorId());
			Seat seat = sector.getSeatBySeatNumber(participant.getSeatNumber());
			
			participant.setTicket(new Ticket(areaByName, sector, seat));
		}
	}


	public void nextPhase() {
		int indexAcutalPhase = phases.indexOf(actualPhase);
		if ((indexAcutalPhase + 1) >= phases.size()) {
			close();
		}else {
			actualPhase = phases.get(indexAcutalPhase+1);
		}
	}
	
	private void close() {
		// TODO close the concert when its done
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
			
			participantsPhase1.add(new Participant(forenname,surname,areaName,sectorId,seatNumber));
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
			
			participantsPhase2.add(new Participant(forenname,surname,areaName,sectorId,seatNumber));
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
			
			participantsPhase3.add(new Participant(forenname,surname,areaName,sectorId,seatNumber));
		}
		
		inputNames.close();
	}

	public IPhase getActualPhase() {
		return actualPhase;
	}
	
	public void setActualPhase(IPhase actualPhase) {
		this.actualPhase = actualPhase;
	}
	public IConcertMediator getEventManager() {
		return eventManager;
	}
	public void setEventManager(IConcertMediator eventManager) {
		this.eventManager = eventManager;
	}
	public ILocation getLocation() {
		return location;
	}
	public void setLocation(ILocation location) {
		this.location = location;
	}
	public ArrayList<Participant> getParticipants() {
		return participantsPhase1;
	}
	
	public void addParticipant(Participant participant) {
		participantsPhase1.add(participant);
	}
	
	public void removeParticipant(Participant participant) {
		if (participantsPhase1.contains(participant)) {
			participantsPhase1.remove(participant);
		}
	}
	
	
}
