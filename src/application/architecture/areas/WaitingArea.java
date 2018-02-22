package application.architecture.areas;

import java.util.ArrayList;

import application.architecture.visualdisplay.VisualDisplay;
import application.persons.Participant;

public abstract class WaitingArea implements IWaitingArea{

	
	protected VisualDisplay visualDisplay;
	protected ArrayList<Participant> participants;
	
	public WaitingArea(VisualDisplay visualDisplay) {
		this.visualDisplay = visualDisplay;
		this.participants = new ArrayList<Participant>();
	}

	public VisualDisplay getVisualDisplay() {
		return visualDisplay;
	}
	public void setVisualDisplay(VisualDisplay visualDisplay) {
		this.visualDisplay = visualDisplay;
	}
	
	@Override
	public void addWaitingParticipant(Participant participant) {
		participants.add(participant);
	}

	@Override
	public void removeWaitingParticipant(Participant participant) {
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
	
	
}
