package application.commands;

import static application.logger.Logger.printInfo;

import java.util.ArrayList;

import application.architecture.areas.IArea;
import application.architecture.sectors.ISector;
import application.architecture.sectors.Seat;
import application.config.AppConfig;
import application.drones.subdrones.IndoorDrone;
import application.drones.subdrones.OutdoorDrone;

public class DroneCommandCollect extends DroneCommand {

	@Override
	public void execute() {

		if (drone instanceof IndoorDrone) {
			if (drone.isFinishedCollecting()) {
				return;
			}
			int currentAreaIndex = drone.getCurrentAreaIndex();
			ArrayList<IArea> indoorAreas = ((IndoorDrone) drone).getIndoorAreas();
			if (currentAreaIndex >= indoorAreas.size()) {
				drone.setFinishedCollecting(true);
				drone.notifyListeners();
				return;
			}
			printInfo("Drone #" + drone.getId() + " starts collecting!");
			IArea currentArea = indoorAreas.get(currentAreaIndex);
			for (int i = 0; i < AppConfig.instance.amountIndoorSectorsPerArea; i++) {
				ISector currentSector = currentArea.getSectorByIndex(i);
				ArrayList<Seat> emptySeats = currentSector.getEmptySeats();
				drone.addEmptySeats(currentArea, currentSector, emptySeats);
			}
		} else if (drone instanceof OutdoorDrone) {
			if (drone.isFinishedCollecting()) {
				return;
			}
			int currentAreaIndex = drone.getCurrentAreaIndex();
			ArrayList<IArea> outdoorAreas = ((OutdoorDrone) drone).getOutdoorAreas();
			if (currentAreaIndex > (AppConfig.instance.amountOutdoorAreas / AppConfig.instance.amountOutdoorDrones)
					- 1) {
				drone.setFinishedCollecting(true);
				drone.notifyListeners();
				return;
			}
			printInfo("Drone #" + drone.getId() + " starts collecting!");
			IArea currentArea = outdoorAreas.get((4 * (drone.getId() - 1)) + currentAreaIndex);
			for (int i = 0; i < AppConfig.instance.amountOutdoorSectorsPerArea; i++) {
				ISector currentSector = currentArea.getSectorByIndex(i);
				ArrayList<Seat> emptySeats = currentSector.getEmptySeats();
				drone.addEmptySeats(currentArea, currentSector, emptySeats);
			}
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		printInfo("Drone #" + drone.getId() + " finished collecting!");
	}

}
