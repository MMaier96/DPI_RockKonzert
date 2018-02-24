package application.persons;

import static application.logger.Logger.printInfo;

import java.util.ArrayList;

import application.commands.DroneCommandCollect;
import application.commands.DroneCommandDepart;
import application.commands.DroneCommandLand;
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
	private boolean isFinished;

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

	private void executeCollectCommand() {
		for (IDrone iDrone : drones) {
			setCommand(new DroneCommandCollect());
			setDrone(iDrone);
			executeActualCommand();
		}
	}

	private void executeDepartCommand() {
		for (IDrone iDrone : drones) {
			setCommand(new DroneCommandDepart());
			setDrone(iDrone);
			executeActualCommand();
		}
	}

	private void executeLandCommand() {
		for (IDrone iDrone : drones) {
			setCommand(new DroneCommandLand());
			setDrone(iDrone);
			executeActualCommand();
		}
	}

	private void executeNextCommand() {
		for (IDrone iDrone : drones) {
			setCommand(new DroneCommandNext());
			setDrone(iDrone);
			executeActualCommand();
		}
	}

	@Override
	public void handleNotification() {
		if (isFinished) {
			return;
		}
		dronesFinished++;
		if (dronesFinished == drones.size()) {
			executeLandCommand();
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

	private void resetPreviousPhase() {
		dronesFinished = 0;
		for (IDrone iDrone : drones) {
			iDrone.setCurrentAreaIndex(0);
			iDrone.setFinishedCollecting(false);
			iDrone.resetResults();
		}
	}

	@Override
	public void setCommand(IDroneCommand command) {
		this.command = command;
	}

	private void setDrone(IDrone iDrone) {
		command.setDrone(iDrone);

	}

	private void sleeps500ms() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void startManaging() {

		resetPreviousPhase();

		concert.nextPhase();
		if (isFinished) {
			return;
		}
		executeDepartCommand();

		sleeps500ms();

		executeCollectCommand();

		sleeps500ms();

		while (dronesFinished < drones.size() && !isFinished) {
			executeNextCommand();
			executeCollectCommand();
		}

	}

	@Override
	public void stopManaging() {
		this.isFinished = true;
	}
}
