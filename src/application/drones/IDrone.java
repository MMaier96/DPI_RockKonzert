package application.drones;

import java.util.ArrayList;

import application.architecture.LocationType;
import application.architecture.areas.IArea;
import application.architecture.sectors.ISector;
import application.architecture.sectors.Seat;

public interface IDrone {
	void addEmptySeats(IArea currentArea, ISector currentSector, ArrayList<Seat> emptySeats);

	void addListener(IDroneListener droneListener);

	int getCurrentAreaIndex();

	int getId();

	ArrayList<IDroneListener> getListeners();

	ArrayList<String> getResultList();

	LocationType getType();

	boolean isFinishedCollecting();

	void notifyListeners();

	void removeListener(IDroneListener droneListener);

	void resetResults();

	void setCurrentAreaIndex(int index);

	void setFinishedCollecting(boolean finishedCollecting);

	void setId(int id);

	void setListeners(ArrayList<IDroneListener> listeners);

	void setType(LocationType type);
}
