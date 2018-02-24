package application.persons;

import application.architecture.visualdisplay.IVisualDisplayListener;
import application.tickets.Ticket;

public class Participant implements IVisualDisplayListener{

	private String forename;
	private String surname;
	private String areaName;
	private int sectorId;
	private int seatNumber;
	private Ticket ticket;
	

	public Participant(String forename, String surname, String areaName, String sectorId, String seatNumber) {
		this.forename = forename;
		this.surname = surname;
		this.areaName = areaName;
		this.sectorId = Integer.parseInt(sectorId);
		this.seatNumber = Integer.parseInt(seatNumber);
	}

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
	
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public int getSectorId() {
		return sectorId;
	}

	public void setSectorId(int sectorId) {
		this.sectorId = sectorId;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	
	@Override
	public void handleNotification() {
		takePlace();
	}

	public void takePlace() {
		ticket.getSeat().setSquatter(this);
	}
	
	
}
