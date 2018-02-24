package application.drones;

import java.util.ArrayList;

import application.architecture.LocationType;
import application.architecture.areas.IArea;
import application.architecture.sectors.ISector;
import application.architecture.sectors.Seat;

public interface IDrone {
	void addListener(IDroneListener droneListener);
	void removeListener(IDroneListener droneListener);
	void notifyListeners();
	void setListeners(ArrayList<IDroneListener> listeners);
	ArrayList<IDroneListener> getListeners();
	int getId();
	void setId(int id);
	LocationType getType();
	void setType(LocationType type);
	
	void setCurrentAreaIndex(int index);
	int getCurrentAreaIndex();
	void addEmptySeats(IArea currentArea, ISector currentSector, ArrayList<Seat> emptySeats);
	void setFinishedCollecting(boolean finishedCollecting);
	boolean isFinishedCollecting();
	
	void resetResults();
	ArrayList<String> getResultList();
}
