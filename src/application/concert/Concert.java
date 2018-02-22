package application.concert;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import application.architecture.ILocation;
import application.architecture.Stadion;
import application.persons.ConcertMediator;
import application.persons.IConcertMediator;
import application.persons.Participant;
import application.phases.IPhase;
import application.phases.Phase1;
import application.phases.Phase2;
import application.phases.Phase3;

import static application.logger.Logger.*;

public class Concert {

	private IPhase actualPhase;
	private IConcertMediator eventManager;
	private ILocation location;
	private ArrayList<Participant> participants;
	private ArrayList<IPhase> phases;
	
	public Concert() {
		printMessage("Concert creation started!");
		printInfo("Building the concert");
		participants = new ArrayList<Participant>();
		
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
		printInfo("loading the participants ...");
		loadParticipants();
		printInfo(participants.size() + " participants were loaded!");
		this.eventManager = new ConcertMediator();
		printInfo("EventManager/Mediator was created!");
		this.location = new Stadion();
		printSeperator();
		printInfo("A stadion was set as the concerts location!");
		
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


	private void loadParticipants() {
		File namesFile = null;
		Scanner inputNames = null;

		try {
			namesFile = new File("data/combined_names.csv");
			inputNames = new Scanner(namesFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (inputNames.hasNextLine()) {
			String actualLine = inputNames.nextLine();
			participants.add(new Participant(actualLine.split(",")[0],actualLine.split(",")[1]));
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
		return participants;
	}
	
	public void addParticipant(Participant participant) {
		participants.add(participant);
	}
	
	public void removeParticipant(Participant participant) {
		if (participants.contains(participant)) {
			participants.remove(participant);
		}
	}
	
	
}
