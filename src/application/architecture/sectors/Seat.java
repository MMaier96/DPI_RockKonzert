package application.architecture.sectors;

import application.persons.Participant;

public class Seat {

	private int seatNumber;
	private Participant squatter;

	public Seat(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public Participant getSquatter() {
		return squatter;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public void setSquatter(Participant squatter) {
		this.squatter = squatter;
	}

}
