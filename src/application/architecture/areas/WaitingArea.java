package application.architecture.areas;

import java.util.ArrayList;

import application.architecture.visualdisplay.VisualDisplay;
import application.persons.Participant;

public abstract class WaitingArea implements IWaitingArea {

	protected VisualDisplay visualDisplay;
	protected ArrayList<Participant> participants;

	public WaitingArea() {
		this.visualDisplay = new VisualDisplay();
		this.participants = new ArrayList<Participant>();
	}

	@Override
	public void addWaitingParticipant(Participant participant) {
		participants.add(participant);
		visualDisplay.addVisualDisplayListener(participant);
	}

	public ArrayList<Participant> getParticipants() {
		return participants;
	}

	@Override
	public int getParticipantsSize() {
		return participants.size();
	}

	public VisualDisplay getVisualDisplay() {
		return visualDisplay;
	}

	@Override
	public void removeWaitingParticipant(Participant participant) {
		if (participants.contains(participant)) {
			participants.remove(participant);
		}
	}

	public void resetParticipants() {
		participants.clear();
	}

	public void setParticipants(ArrayList<Participant> participants) {
		this.participants = participants;
	}

	public void setVisualDisplay(VisualDisplay visualDisplay) {
		this.visualDisplay = visualDisplay;
	}

}
