package application.commands;

import static application.logger.Logger.*;

import application.drones.subdrones.IndoorDrone;
import application.drones.subdrones.OutdoorDrone;

public class DroneCommandNext extends DroneCommand {

	@Override
	public void execute() {
		drone.setCurrentAreaIndex(drone.getCurrentAreaIndex() + 1);
		if (drone instanceof IndoorDrone && drone.getCurrentAreaIndex() >= 1) {
			return;
		}

		if (drone instanceof OutdoorDrone && drone.getCurrentAreaIndex() >= 4) {
			return;
		}
		printInfo("Drone # " + drone.getId() + " flying to next area!");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		printInfo("Drone # " + drone.getId() + " arrived at next area!");
	}

}
