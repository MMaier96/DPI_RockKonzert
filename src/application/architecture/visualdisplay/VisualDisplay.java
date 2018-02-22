package application.architecture.visualdisplay;

import java.util.ArrayList;

import application.persons.IConcertMediatorListener;
import application.persons.Participant;
import application.phases.IPhase;

public class VisualDisplay implements IConcertMediatorListener{
	
	private ArrayList<Participant> participants;
	private IPhase actualPhase;
	
	public VisualDisplay() {
		participants = new ArrayList<Participant>();
	}
	
	public void addParticipant(Participant participant) {
		participants.add(participant);
	}
	
	
	public void removeParticipant(Participant participant) {
		if (participants.contains(participant)) {
			participants.remove(participant);
		}
	}
	
	public ArrayList<Participant> getParticipants() {
		return participants;
	}


	public void setParticipants(ArrayList<Participant> participants) {
		this.participants = participants;
	}


	public IPhase getActualPhase() {
		return actualPhase;
	}


	public void setActualPhase(IPhase actualPhase) {
		this.actualPhase = actualPhase;
	}


	public void notifyListeners() {
		for (Participant participant : participants) {
			participant.handleNotification();
		}
	}
	
	@Override
	public void handleNotification() {
		// TODO: handle event from mediator
	}

}
