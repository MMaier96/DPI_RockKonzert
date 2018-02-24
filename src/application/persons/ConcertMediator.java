package application.persons;

import static application.logger.Logger.printInfo;

import java.util.ArrayList;

import application.commands.DroneCommandCollect;
import application.commands.DroneCommandDepart;
import application.commands.DroneCommandNext;
import application.commands.IDroneCommand;
import application.concert.Concert;
import application.config.AppConfig;
import application.drones.IDrone;
import application.drones.IDroneListener;
import application.drones.subdrones.IndoorDrone;
import application.drones.subdrones.OutdoorDrone;

public class ConcertMediator implements IDroneListener, IConcertMediator {

	protected IDroneCommand command;
	protected ArrayList<IDrone> drones;
	protected ArrayList<IConcertMediatorListener> displays;
	protected Concert concert;
	private int dronesFinished = 0;

	public ConcertMediator(Concert concert) {
		this.concert = concert;
		this.drones = new ArrayList<IDrone>();
		this.displays = new ArrayList<IConcertMediatorListener>();
		printInfo("EventManager/Mediator was created!");

		for (int i = 0; i < AppConfig.instance.amountIndoorDrones; i++) {
			registerDrone(new IndoorDrone(i, concert.getLocation().getIndoorAreas()));
		}
		printInfo(AppConfig.instance.amountIndoorDrones + " IndoorDrone was added!");

		for (int i = AppConfig.instance.amountIndoorDrones; i < (AppConfig.instance.amountIndoorDrones
				+ AppConfig.instance.amountOutdoorDrones); i++) {
			registerDrone(new OutdoorDrone(i, concert.getLocation().getOutdoorAreas()));
		}
		printInfo(AppConfig.instance.amountOutdoorDrones + " OutdoorDrones were added!");

		for (IDrone iDrone : drones) {
			iDrone.addListener(this);
		}
		printInfo("Added the mediator as listener to all drones");
	}

	@Override
	public void addConcertMediatorListener(IConcertMediatorListener listener) {
		displays.add(listener);
	}

	@Override
	public void executeActualCommand() {
		command.execute();
	}

	@Override
	public void handleNotification() {
		dronesFinished++;
		if (dronesFinished == drones.size()) {
			for (IDrone iDrone : drones) {
				printInfo("Drone #" + iDrone.getId() + " spotted following empty seats: " + iDrone.getResultList());
			}
			notifyListeners();
			startManaging();
		}
	}

	@Override
	public void notifyListeners() {
		for (IConcertMediatorListener iConcertMediatorListener : displays) {
			iConcertMediatorListener.handleNotification();
		}
	}

	@Override
	public void registerDrone(IDrone drone) {
		drones.add(drone);
	}

	@Override
	public void removeConcertMediatorListener(IConcertMediatorListener listener) {
		if (displays.contains(listener)) {
			displays.remove(listener);
		}
	}

	@Override
	public void setCommand(IDroneCommand command) {
		this.command = command;
	}

	@Override
	public void startManaging() {
		// reset
		dronesFinished = 0;
		for (IDrone iDrone : drones) {
			iDrone.setCurrentAreaIndex(0);
			iDrone.setFinishedCollecting(false);
			iDrone.resetResults();
		}

		concert.nextPhase();
		for (IDrone iDrone : drones) {
			command = new DroneCommandDepart();
			command.setDrone(iDrone);
			command.execute();
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (IDrone iDrone : drones) {
			command = new DroneCommandCollect();
			command.setDrone(iDrone);
			command.execute();
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while (dronesFinished < drones.size()) {
			for (IDrone iDrone : drones) {
				command = new DroneCommandNext();
				command.setDrone(iDrone);
				command.execute();
			}
			for (IDrone iDrone : drones) {
				command = new DroneCommandCollect();
				command.setDrone(iDrone);
				command.execute();
			}
		}
	}
}
