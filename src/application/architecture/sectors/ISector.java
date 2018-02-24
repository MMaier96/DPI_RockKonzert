package application.architecture.sectors;

import java.util.ArrayList;

import application.architecture.areas.IArea;

public interface ISector {
	ArrayList<Seat> getEmptySeats();

	IArea getRelatedArea();

	Seat getSeatBySeatNumber(int seatNumber);

	int getSeatsAmount();

	int getSectorId();

	String getSectorName();

	void setRelatedArea(IArea area);

	void setSectorId(int id);
}
