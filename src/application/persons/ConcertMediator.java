package application.persons;

import java.util.ArrayList;

import application.architecture.visualdisplay.IVisualDisplayListener;
import application.commands.IDroneCommand;
import application.drones.IDrone;
import application.drones.IDroneListener;

public class ConcertMediator implements IDroneListener, IConcertMediator {

	protected IDroneCommand command;
	protected ArrayList<IDrone> drones;
	protected ArrayList<IVisualDisplayListener> displays;

	public ConcertMediator() {
		this.drones = new ArrayList<IDrone>();
		this.displays = new ArrayList<IVisualDisplayListener>();
	}

	@Override
	public void registerDrone(IDrone drone) {
		drones.add(drone);
	}

	@Override
	public void setCommand(IDroneCommand command) {
		this.command = command;
	}

	@Override
	public void executeActualCommand() {
		command.execute();
	}

	@Override
	public void addVisualDisplayListener(IVisualDisplayListener listener) {
		displays.add(listener);
	}

	@Override
	public void removeVisualDisplayListener(IVisualDisplayListener listener) {
		if (displays.contains(listener)) {
			displays.remove(listener);
		}
	}

	@Override
	public void notifyListeners() {
		for (IVisualDisplayListener iVisualDisplayListener : displays) {
			iVisualDisplayListener.handleNotification();
		}
	}

	@Override
	public void handleNotification() {
		// TODO: handle notification from drone
	}

}
