package application.architecture.areas;

import application.architecture.visualdisplay.VisualDisplay;
import application.persons.Participant;

public interface IWaitingArea {

	void addWaitingParticipant(Participant participant);
	void removeWaitingParticipant(Participant participant);
	void setVisualDisplay(VisualDisplay visualDisplay);
	VisualDisplay getVisualDisplay();
}
