package application.drones;

import java.util.ArrayList;

public abstract class Drone implements IDrone{

	protected ArrayList<IDroneListener> listeners;
	
	public Drone() {
		this.listeners = new ArrayList<IDroneListener>();
	}
	
	@Override
	public void addListener(IDroneListener droneListener) {
		listeners.add(droneListener);
	}

	@Override
	public void removeListener(IDroneListener droneListener) {
		if (listeners.contains(droneListener)) {
			listeners.remove(droneListener);
		}
	}

	@Override
	public ArrayList<IDroneListener> getListeners() {
		return listeners;
	}
	
	@Override
	public void setListeners(ArrayList<IDroneListener> listeners) {
		this.listeners = listeners;
	}

	@Override
	public void notifyListeners() {
		for (IDroneListener iDroneListener : listeners) {
			iDroneListener.handleNotification();
		}
	}

}
