package application.architecture.areas;

import application.architecture.visualdisplay.VisualDisplay;
import application.persons.Participant;

public interface IWaitingArea {

	void addWaitingParticipant(Participant participant);

	int getParticipantsSize();

	VisualDisplay getVisualDisplay();

	void removeWaitingParticipant(Participant participant);

	void resetParticipants();

	void setVisualDisplay(VisualDisplay visualDisplay);
}
