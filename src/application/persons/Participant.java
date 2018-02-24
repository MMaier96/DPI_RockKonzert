package application.persons;

import application.architecture.visualdisplay.IVisualDisplayListener;
import application.tickets.Ticket;

public class Participant implements IVisualDisplayListener {

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

	public String getAreaName() {
		return areaName;
	}

	public String getForename() {
		return forename;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public int getSectorId() {
		return sectorId;
	}

	public String getSurname() {
		return surname;
	}

	public Ticket getTicket() {
		return ticket;
	}

	@Override
	public void handleNotification() {
		takePlace();
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public void setSectorId(int sectorId) {
		this.sectorId = sectorId;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public void takePlace() {
		ticket.getSeat().setSquatter(this);
	}

}
