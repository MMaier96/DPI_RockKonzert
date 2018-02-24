package application.architecture.sectors;

import java.util.ArrayList;

import application.architecture.areas.IArea;

public abstract class Sector implements ISector {

	protected ArrayList<Seat> seats;
	protected int seatsAmount;
	protected int sectorId;
	protected IArea relatedArea;

	public Sector(int seatsAmount, IArea area, int id) {
		this.relatedArea = area;
		this.seatsAmount = seatsAmount;
		this.sectorId = id;
		this.seats = new ArrayList<Seat>(seatsAmount);

		createSeats();
	}

	private void createSeats() {
		for (int i = 1; i <= seatsAmount; i++) {
			seats.add(new Seat(i));
		}
	}

	@Override
	public ArrayList<Seat> getEmptySeats() {
		ArrayList<Seat> emptySeats = new ArrayList<Seat>();
		for (Seat seat : seats) {
			if (seat.getSquatter() == null) {
				emptySeats.add(seat);
			}
		}
		return emptySeats;
	}

	@Override
	public IArea getRelatedArea() {
		return relatedArea;
	}

	@Override
	public Seat getSeatBySeatNumber(int seatNumber) {
		for (Seat seat : seats) {
			if (seat.getSeatNumber() == seatNumber) {
				return seat;
			}
		}
		return null;
	}

	@Override
	public int getSeatsAmount() {
		return seatsAmount;
	}

	@Override
	public int getSectorId() {
		return sectorId;
	}

	@Override
	public String getSectorName() {
		StringBuilder builder = new StringBuilder().append(relatedArea.getName())
				.append(String.format("%02d", sectorId));
		return builder.toString();
	}

	@Override
	public void setRelatedArea(IArea area) {
		relatedArea = area;
	}

	@Override
	public void setSectorId(int id) {
		sectorId = id;
	}

	@Override
	public String toString() {
		return getSectorName();
	}
}
