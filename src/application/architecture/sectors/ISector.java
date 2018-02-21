package application.architecture.sectors;

import application.architecture.areas.IArea;

public interface ISector {
	IArea getRelatedArea();
	Seat getSeatBySeatNumber(int seatNumber);
	int getSeatsAmount();
	int getSectorId();
	String getSectorName();
	void setRelatedArea(IArea area);
	void setSectorId(int id);
}
