package application.drones;

import java.util.ArrayList;

public interface IDrone {
	void addListener(IDroneListener droneListener);
	void removeListener(IDroneListener droneListener);
	void notifyListeners();
	void setListeners(ArrayList<IDroneListener> listeners);
	ArrayList<IDroneListener> getListeners();
}
