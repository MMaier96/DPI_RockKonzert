package application.persons;

import application.architecture.visualdisplay.IVisualDisplayListener;
import application.tickets.Ticket;

public class Participant implements IVisualDisplayListener{

	private String forename;
	private String surname;
	private Ticket ticket;
	
	public String getForename() {
		return forename;
	}
	public void setForename(String forename) {
		this.forename = forename;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	@Override
	public void handleNotification() {
		// TODO handle status from display
		
	}
	
	
}
