package application.concert;

import java.util.ArrayList;

import application.architecture.ILocation;
import application.persons.IConcertMediator;
import application.persons.Participant;
import application.phases.IPhase;

public class Concert {

	private IPhase actualPhase;
	private IConcertMediator eventManager;
	private ILocation location;
	private ArrayList<Participant> participants;
	
	public Concert() {
		// TODO: create Constructor
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
